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

@WebServlet("/Habilitar")
public class HabilitarServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PersonaService personaService = new PersonaServiceImp();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String dni = request.getParameter("nroDocumento");
		boolean estado = Boolean.parseBoolean(request.getParameter("habilitadoVotar"));

		try {
			personaService.conmutarHabilitacion(Integer.parseInt(dni), estado);

			PrintWriter out = response.getWriter();
			out.print("{\"ok\":true,\"habilitado\":" + estado + "}");
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			PrintWriter out = response.getWriter();
			out.print("{\"ok\":false,\"error\":\"" + e.getMessage().replace("\"", "'") + "\"}");
			out.flush();
		}
	}
}