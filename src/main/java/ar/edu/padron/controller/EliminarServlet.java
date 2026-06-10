package ar.edu.padron.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ar.edu.padron.service.PersonaService;
import ar.edu.padron.service.PersonaServiceImp;

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
