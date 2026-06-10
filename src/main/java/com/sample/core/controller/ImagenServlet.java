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

@WebServlet("/Imagen")
public class ImagenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// Inyectamos tu implementación existente de servicio
	private PersonaService personaService = new PersonaServiceImp();

    public ImagenServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. Obtener el parámetro ID de la persona que viaja en la URL (?id=X)
			String idParam = request.getParameter("id");
			
			if (idParam != null && !idParam.trim().isEmpty()) {
				int idPersona = Integer.parseInt(idParam);

				// 2. Invocar tu método del Service para recuperar el LONGBLOB desde la BD
				byte[] fotoBytes = personaService.obtenerFotoDni(idPersona);

				// 3. Si la base de datos devolvió bytes válidos, se los enviamos al navegador
				if (fotoBytes != null && fotoBytes.length > 0) {
					response.setContentType("image/jpeg"); // Informamos que es un binario de tipo imagen
					response.setContentLength(fotoBytes.length);
					
					// Escribimos el flujo binario directo en el canal de salida de la respuesta
					response.getOutputStream().write(fotoBytes);
					response.getOutputStream().flush();
					return;
				}
			}
			
			// 4. Si no tiene foto o el ID es inválido, redirigimos a una silueta genérica
			response.sendRedirect(request.getContextPath() + "/assets/images/no-avatar.png");
			
		} catch (Exception e) {
			System.out.println("Error en ImagenServlet al recuperar binario: " + e.getMessage());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Redirigimos el flujo al GET para asegurar compatibilidad total
		doGet(request, response);
	}
}
