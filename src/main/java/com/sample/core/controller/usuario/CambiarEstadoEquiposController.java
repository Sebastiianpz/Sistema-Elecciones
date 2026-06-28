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

@WebServlet("/cambiarEstadoEquipo")
public class CambiarEstadoEquiposController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioService usuarioService = new UsuarioServiceImp();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String idParam = request.getParameter("id");
            String estadoParam = request.getParameter("estadoPc");

            if (idParam == null || idParam.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"ok\":false,\"error\":\"Falta el par�metro requerido (id).\"}");
                out.flush();
                return;
            }

            if (estadoParam == null || estadoParam.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"ok\":false,\"error\":\"Falta el par�metro requerido (estadoPc).\"}");
                out.flush();
                return;
            }

            int id = Integer.parseInt(idParam);
            boolean estadoPc = Boolean.parseBoolean(estadoParam);

            usuarioService.cambiarEstadoEquipos(id, estadoPc);

            out.print("{\"ok\":true,\"mensaje\":\"�Estado de la terminal actualizado con �xito!\"}");
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try (PrintWriter out = response.getWriter()) {
                out.print("{\"ok\":false,\"error\":\"Error interno: " + e.getMessage() + "\"}");
            }
        }
    }
}