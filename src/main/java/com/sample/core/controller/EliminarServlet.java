package com.sample.core.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.sample.core.service.PersonaService;
import com.sample.core.service.PersonaServiceImp;

@WebServlet("/Eliminar")
public class EliminarServlet extends HttpServlet {

	private PersonaService personaService = new PersonaServiceImp();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		try {

			String dni = request.getParameter("nroDocumento");

			personaService.eliminarPersona(dni);

			out.print("""
					    {
					        "ok": true,
					        "mensaje": "Ciudadano eliminado correctamente"
					    }
					""");

		} catch (Exception e) {

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

			out.print("""
					    {
					        "ok": false,
					        "error": "%s"
					    }
					""".formatted(e.getMessage()));
		}

		out.flush();
	}
}
