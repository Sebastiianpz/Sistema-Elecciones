package com.sample.core.controller.usuario;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.core.domain.Usuario;
import com.sample.core.service.UsuarioService;
import com.sample.core.service.UsuarioServiceImp;

@WebServlet("/buscarCandidatoPorId")
public class BuscarCandidatoPorIdController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioService usuarioService = new UsuarioServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            int id = Integer.parseInt(request.getParameter("id"));
            
            // Buscamos el candidato único en el servicio
            Usuario candidato = usuarioService.findCandidatoByid(id);
            
            if (candidato != null) {
                // Armamos el objeto JSON a mano con llaves
                out.print("{");
                out.print("\"id\":" + candidato.getId() + ",");
                
                // Recordá cambiar .getNombre() por el método real de tu Domain (ej: getUsername)
                out.print("\"nombreCompleto\":\"" + candidato.getNombreCompleto() + "\"");
                // Si tu compañera necesita más campos en el formulario, los sumás acá abajo con una coma inicial
                
                out.print("}");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND); // Error 404
                out.print("{");
                out.print("\"ok\":false,");
                out.print("\"error\":\"Candidato no encontrado.\"");
                out.print("}");
            }
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Error 500
            
            try (PrintWriter out = response.getWriter()) {
                out.print("{");
                out.print("\"ok\":false,");
                out.print("\"error\":\"Error al buscar el candidato: " + e.getMessage().replace("\"", "'") + "\"");
                out.print("}");
            }
        }
    }
}