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
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			String macActual = obtenerMacDeEstaPc();
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
		try {
			// Ejecuta el comando nativo de Linux gracias al volumen mapeado en docker-compose
			ProcessBuilder pb = new ProcessBuilder("cat", "/sys/class/net/enp0s3/address");
			Process proceso = pb.start();

			BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			String macReal = lector.readLine();
			lector.close();

			if (macReal != null && !macReal.trim().isEmpty()) {
				// Transforma la respuesta de "08:00:27:8c:4e:16" a "08-00-27-8C-4E-16"
				String macFormateada = macReal.trim().toUpperCase().replace(":", "-");
				System.out.println("MAC Real del Host encontrada: " + macFormateada);
				return macFormateada;
			}
		} catch (Exception e) {
			System.out.println("Error al ejecutar ProcessBuilder en el Host Linux: " + e.getMessage());
		}
		
		// Retorno por defecto si el archivo no es accesible
		return "00-00-00-00-00-00";
	}
}