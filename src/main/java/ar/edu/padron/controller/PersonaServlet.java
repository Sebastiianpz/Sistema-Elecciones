package ar.edu.padron.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import ar.edu.padron.domain.Persona;
import ar.edu.padron.service.PersonaService;
import ar.edu.padron.service.PersonaServiceImp;
import ar.edu.padron.enums.*;

@WebServlet("/Persona")
@MultipartConfig
public class PersonaServlet extends HttpServlet {
	private PersonaService personaService = new PersonaServiceImp();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		try {
			// Extraer campos de texto
			Persona p = new Persona();
			p.setNroDocumento(request.getParameter("dni"));
			p.setApellido(request.getParameter("apellido"));
			p.setNombre(request.getParameter("nombre"));
			p.setFechaNacimiento(LocalDate.parse(request.getParameter("fechaNac")));
			p.setSexo(SexoEnum.valueOf(request.getParameter("sexo")));
			p.setDomicilio(request.getParameter("domicilio"));
			p.setHabilitadoVotar(true);

			// Extraer Imagen
			Part part = request.getPart("imagen");
			byte[] foto = null;
			String nombreFoto = null;

			if (part != null && part.getSize() > 0) {
				nombreFoto = part.getSubmittedFileName();
				// try-with-resources garantiza el cierre del stream incluso ante excepciones
				try (java.io.InputStream is = part.getInputStream()) {
					foto = is.readAllBytes();
				}
			}

			// Llamada al Service
			personaService.registrarPersona(p, foto, nombreFoto);

			// Respuesta JSON manual
			out.print("{\"ok\": true, \"mensaje\": \"Guardado con éxito\"}");

		} catch (Exception e) {
			response.setStatus(400);
			out.print("{\"ok\": false, \"errores\": [\"" + e.getMessage() + "\"]}");
		}
	}
}
