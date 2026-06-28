package com.sample.core.controller.usuario;

import java.io.IOException;
import java.io.PrintWriter;

import com.sample.core.service.UsuarioService;
import com.sample.core.service.UsuarioServiceImp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/guardarCandidato")
public class GuardarCandidatoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioService usuarioService = new UsuarioServiceImp();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Usamos el try con recursos para el PrintWriter, protegiendo el flujo
        try (PrintWriter out = response.getWriter()) {
            
            // 1. Levantamos los parámetros exactos desde el formulario AJAX
            String nombreCompleto = request.getParameter("nombreCompleto");
            String partido = request.getParameter("partido");
            int numPartido = Integer.parseInt(request.getParameter("numPartido"));
            String colorPartido = request.getParameter("colorPartido"); 
            
            usuarioService.saveCandidato(nombreCompleto, partido, numPartido, colorPartido);

            // 3. Respuesta JSON manual 
            out.print("{");
            out.print("\"ok\":true,");
            out.print("\"mensaje\":\"Candidato registrado con éxito.\"");
            out.print("}");
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            
            try (PrintWriter out = response.getWriter()) {
                out.print("{");
                out.print("\"ok\":false,");
                out.print("\"error\":\"Error al guardar el candidato: " + e.getMessage().replace("\"", "'") + "\"");
                out.print("}");
                out.flush();
            }
        }
    }
}