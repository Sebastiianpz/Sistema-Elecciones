package com.sample.core.controller.usuario;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.core.service.UsuarioService;
import com.sample.core.service.UsuarioServiceImp;

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
        PrintWriter out = response.getWriter();

        try {
            String nombreCompleto = request.getParameter("nombreCompleto");
            String partido = request.getParameter("partido");
            int numPartido = Integer.parseInt(request.getParameter("numPartido"));
            // Agrega aquí el cuarto parámetro que pida tu firma (ej: String cargo)
            String cargo = request.getParameter("cargo"); 

            // Llamamos al método de tu interfaz UsuarioService
            usuarioService.saveCandidato(nombreCompleto, partido, numPartido, cargo);

            out.print("{\"ok\":true,\"mensaje\":\"Candidato registrado con éxito.\"}");
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"ok\":false,\"error\":\"Error al guardar el candidato.\"}");
            out.flush();
        }
    }
}