package com.sample.core.controller.usuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.sample.core.domain.Usuario;
import com.sample.core.service.UsuarioService;
import com.sample.core.service.UsuarioServiceImp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ingresarMesa")
public class ValidarEquipoController extends HttpServlet { 

	private static final long serialVersionUID = 1L;
	private UsuarioService usuarioService = new UsuarioServiceImp();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("========================================");
System.out.println("ENTRO AL NUEVO VALIDAR EQUIPO");
System.out.println("========================================");

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			String macActual = obtenerMacDeEstaPc();
			System.out.println("EJECUTANDO NUEVO METODO obtenerMacDeEstaPc()");
			System.out.println("PC intentando entrar con MAC real del Host: " + macActual);

			List<Usuario> equipos = usuarioService.listEquipos();
			Usuario pcEncontrada = null;

			for (Usuario pc : equipos) {
				if (pc.getMacAddress() != null && pc.getMacAddress().equalsIgnoreCase(macActual)) {
					pcEncontrada = pc;
					break;
				}
			}

			if (pcEncontrada == null) {
				String json = String.format("{\"permitido\": false, \"mensajeError\": \"%s\"}", 
						"Este equipo no está registrado para el padrón electoral.");
				response.getWriter().write(json);
				return;
			}

			boolean estaHabilitada = pcEncontrada.isEstadoPc(); 

			if (!estaHabilitada) {
				String json = String.format("{\"permitido\": false, \"mensajeError\": \"%s\"}", 
						"MESA BLOQUEADA: Terminal deshabilitada por el administrador.");
				response.getWriter().write(json);
				return;
			}

			request.getSession().setAttribute("idPcMesa", pcEncontrada.getId());
			
			response.getWriter().write("{\"permitido\": true}");

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			response.getWriter().write("{\"permitido\": false, \"mensajeError\": \"Error interno en el servidor.\"}");
		}
	}

	// 🌟 METODO MODIFICADO: Ahora lee el hardware de Ubuntu saliendo de Docker
	private String obtenerMacDeEstaPc() throws Exception {

	    String ruta = "/sys/class/net/enp0s3/address";

	    System.out.println("Leyendo MAC desde: " + ruta);

	    ProcessBuilder pb = new ProcessBuilder("cat", ruta);

	    Process proceso = pb.start();

	    BufferedReader ok = new BufferedReader(
	            new InputStreamReader(proceso.getInputStream()));

	    BufferedReader err = new BufferedReader(
	            new InputStreamReader(proceso.getErrorStream()));

	    String mac = ok.readLine();
	    String error = err.readLine();

	    proceso.waitFor();

	    if (error != null) {
	        System.out.println("ERROR: " + error);
	    }

	    if (mac != null) {
	        mac = mac.trim().toUpperCase().replace(":", "-");
	        System.out.println("MAC encontrada: " + mac);
	        return mac;
	    }

	    return "00-00-00-00-00-00";
	}
}
