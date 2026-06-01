package com.sample.core.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.sample.core.service.PersonaService; 
import com.sample.core.service.PersonaServiceImp; 

@WebServlet("/Eliminar")
public class EliminarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PersonaService personaService = new PersonaServiceImp();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dni = request.getParameter("nroDocumento");
		try {
			// Ejecuta el borrado directo en el Service y DAO
			personaService.eliminarPersona(dni);
			response.setStatus(HttpServletResponse.SC_OK); // 200 OK
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Error
		}
	}
}
