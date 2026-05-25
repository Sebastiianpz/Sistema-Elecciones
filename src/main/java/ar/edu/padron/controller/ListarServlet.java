package ar.edu.padron.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.padron.domain.Persona;
import ar.edu.padron.service.PersonaService; 
import ar.edu.padron.service.PersonaServiceImp; 

@WebServlet("/Listar")
public class ListarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private PersonaService personaService = new PersonaServiceImp();

	// Usamos doPost o doGet indistintamente, procesando todo en un solo lugar
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. Configuramos que la respuesta será un texto tipo JSON con caracteres correctos
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		try {
			// 2. Pedimos la lista al servicio
			List<Persona> lista = personaService.listarPadron();
			
			// 3. Armamos el JSON manualmente concatenando cadenas (Texto plano estructurado)
			StringBuilder json = new StringBuilder();
			json.append("["); // Inicia la lista
			
			for (int i = 0; i < lista.size(); i++) {
				Persona p = lista.get(i);
				
				json.append("{");
				json.append("\"nroDocumento\":\"").append(p.getNroDocumento()).append("\",");
				json.append("\"apellido\":\"").append(p.getApellido()).append("\",");
				json.append("\"nombre\":\"").append(p.getNombre()).append("\",");
				json.append("\"habilitadoVotar\":").append(p.isHabilitadoVotar());
				json.append("}");
				
				// Si no es la última persona, agregamos una coma para separar los objetos
				if (i < lista.size() - 1) {
					json.append(",");
				}
			}
			json.append("]"); // Cierra la lista
			
			// 4. Enviamos el texto estructurado de respuesta al AJAX
			out.print(json.toString());
			
		} catch (Exception e) {
			// Si falla, enviamos código 500 y el mensaje de error (igual que hiciste en Rodado)
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.print("{\"error\": \"" + e.getMessage() + "\"}");
			e.printStackTrace();
		} finally {
			out.flush();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
