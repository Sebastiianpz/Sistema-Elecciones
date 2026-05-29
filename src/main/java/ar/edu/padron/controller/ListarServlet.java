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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		try {
			List<Persona> lista = personaService.listarPadron();

			StringBuilder json = new StringBuilder();
			json.append("[");

			for (int i = 0; i < lista.size(); i++) {
				Persona p = lista.get(i);

				json.append("{");
				// 1. SUMAMOS EL ID NUMÉRICO (Esto soluciona el "id=undefined" en el Front-end)
				json.append("\"id\":").append(p.getId()).append(",");

				json.append("\"nroDocumento\":\"").append(p.getNroDocumento()).append("\",");
				json.append("\"apellido\":\"").append(p.getApellido()).append("\",");
				json.append("\"nombre\":\"").append(p.getNombre()).append("\",");

				// 2. SUMAMOS LOS CAMPOS EXTRA (Para que el Modal Modificar cargue todos los
				// datos)
				json.append("\"domicilio\":\"").append(p.getDomicilio() != null ? p.getDomicilio() : "").append("\",");
				json.append("\"fechaNacimiento\":\"")
						.append(p.getFechaNacimiento() != null ? p.getFechaNacimiento().toString() : "").append("\",");
				json.append("\"sexo\":\"").append(p.getSexo() != null ? p.getSexo().name() : "").append("\",");

				json.append("\"habilitadoVotar\":").append(p.isHabilitadoVotar());
				json.append("}");

				if (i < lista.size() - 1) {
					json.append(",");
				}
			}
			json.append("]");

			out.print(json.toString());

		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.print("{\"error\": \"" + e.getMessage() + "\"}");
			e.printStackTrace();
		} finally {
			out.flush();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
