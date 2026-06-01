package ar.edu.padron.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ar.edu.padron.service.PersonaService; 
import ar.edu.padron.service.PersonaServiceImp; 

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
