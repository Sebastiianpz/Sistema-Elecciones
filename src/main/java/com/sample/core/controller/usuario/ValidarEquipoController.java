package com.sample.core.controller.usuario;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.core.domain.Usuario; 
import com.sample.core.service.UsuarioService;
import com.sample.core.service.UsuarioServiceImp;

@WebServlet("/ingresarMesa")
public class ValidarEquipoController extends HttpServlet { // 🚨 ARREGLADO: Faltaba el extends

	private static final long serialVersionUID = 1L;
	private UsuarioService usuarioService = new UsuarioServiceImp();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			String macActual = obtenerMacDeEstaPc();
			System.out.println("PC intentando entrar con MAC: " + macActual);

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

	private String obtenerMacDeEstaPc() throws Exception {
		InetAddress ip = InetAddress.getLocalHost();
		NetworkInterface network = NetworkInterface.getByInetAddress(ip);
		if (network == null) return "00-00-00-00-00-00";
		
		byte[] mac = network.getHardwareAddress();
		if (mac == null) return "00-00-00-00-00-00";

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mac.length; i++) {
			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
		}
		return sb.toString(); 
	}
}