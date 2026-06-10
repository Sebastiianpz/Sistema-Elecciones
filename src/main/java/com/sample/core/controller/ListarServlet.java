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

@WebServlet("/Listar")
public class ListarServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private PersonaService personaService = new PersonaServiceImp();

	private Gson gson = new GsonBuilder()
			.registerTypeAdapter(LocalDate.class,
					(JsonSerializer<LocalDate>) (src, typeOfSrc, context) -> new JsonPrimitive(src.toString()))
			.create();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try (PrintWriter out = response.getWriter()) {

			List<Persona> lista = personaService.listarPadron();

			out.print(gson.toJson(lista));

		} catch (Exception e) {

			e.printStackTrace();

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

			try (PrintWriter out = response.getWriter()) {

				out.print("{");
				out.print("\"ok\":false,");
				out.print("\"error\":\"" + e.getMessage().replace("\"", "'") + "\"");
				out.print("}");
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}