package com.sample.core.controller.voto;

import java.io.IOException;

import com.sample.core.service.VotosService;
import com.sample.core.service.VotosServiceImp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/guardarVoto")
public class VotoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private VotosService votosService = new VotosServiceImp();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			int personaId = Integer.parseInt(request.getParameter("personaId"));
			int candidatoId = Integer.parseInt(request.getParameter("candidatoId"));
			
			HttpSession session = request.getSession();
			Integer pcIdReal = (Integer) session.getAttribute("idPcMesa");

			if (pcIdReal == null) {
				pcIdReal = Integer.parseInt(request.getParameter("pcId"));
				System.out.println("No se encontr� idPcMesa en sesi�n, usando pcId del par�metro.");
			} else {
				System.out.println("Voto recibido. PC ID real de la sesi�n: " + pcIdReal);
			}

			java.sql.Date fechaVoto = new java.sql.Date(System.currentTimeMillis());

			votosService.save(personaId, candidatoId, pcIdReal);
			
			String jsonExito = String.format("{\"success\": %b, \"mensaje\": \"%s\"}", true, "Voto registrado con �xito");
			response.getWriter().write(jsonExito);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			
			String jsonError = String.format("{\"success\": %b, \"error\": \"%s\"}", false, "No se pudo registrar el voto en el servidor.");
			response.getWriter().write(jsonError);
		}
	}
}