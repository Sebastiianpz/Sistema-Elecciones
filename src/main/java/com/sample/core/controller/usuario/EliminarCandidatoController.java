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

@WebServlet("/eliminarCandidato")
public class EliminarCandidatoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioService usuarioService = new UsuarioServiceImp();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            usuarioService.deleteCandidato(id);
            out.print("{\"ok\":true,\"mensaje\":\"Candidato eliminado correctamente.\"}");

        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"ok\":false,\"error\":\"Error al eliminar el candidato.\"}");
        }

        out.flush();
    }
}
