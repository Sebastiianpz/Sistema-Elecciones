package com.sample.core.controller;

import java.io.IOException;
<<<<<<< HEAD:src/main/java/ar/edu/padron/controller/EliminarServlet.java
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ar.edu.padron.service.PersonaService;
import ar.edu.padron.service.PersonaServiceImp;
=======
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.sample.core.service.PersonaService; 
import com.sample.core.service.PersonaServiceImp; 
>>>>>>> f95916c69f48519981b847095dfb7790c71c5785:src/main/java/com/sample/core/controller/EliminarServlet.java

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
