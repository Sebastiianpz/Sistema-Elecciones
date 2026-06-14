package com.sample.core.controller.voto;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.core.service.VotosService;
import com.sample.core.service.VotosServiceImp;

@WebServlet("/guardarVoto")
public class VotoController {

	private VotosService votosService = new VotosServiceImp();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1. Levantás los IDs que vienen de la interfaz (JSP / AJAX)
            int personaId = Integer.parseInt(request.getParameter("personaId"));
            int candidatoId = Integer.parseInt(request.getParameter("candidatoId"));
            int pcId = Integer.parseInt(request.getParameter("pcId")); // Computadora/Mesa de votación
            java.sql.Date fechaVoto = new java.sql.Date(System.currentTimeMillis());
            // 2. Llamás al método save del Service que me mostraste
            votosService.save(personaId, candidatoId, pcId);
            
            // 3. Respondés que todo salió bien (si es por AJAX podés mandar un texto simple)
            response.getWriter().write("Voto registrado con éxito");
            
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}