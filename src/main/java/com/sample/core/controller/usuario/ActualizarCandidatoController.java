package com.sample.core.controller.usuario;

import java.io.IOException;
import java.io.PrintWriter;

import com.sample.core.domain.Usuario;
import com.sample.core.service.UsuarioService;
import com.sample.core.service.UsuarioServiceImp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/actualizarCandidato")
public class ActualizarCandidatoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UsuarioService usuarioService = new UsuarioServiceImp();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String jsonRespuesta;

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombreCompleto = request.getParameter("nombreCompleto");
            String partido = request.getParameter("partido");
            String colorPartido = request.getParameter("colorPartido");

            Usuario candidato = new Usuario();
            candidato.setId(id);
            candidato.setNombreCompleto(nombreCompleto);
            candidato.setPartido(partido);
            candidato.setColorPartido(colorPartido);

            usuarioService.updateCandidato(candidato);

            jsonRespuesta = "{\"ok\":true,\"mensaje\":\"Candidato actualizado correctamente\"}";

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            String mensajeError = (e.getMessage() != null) ? e.getMessage().replace("\"", "'") : "Error interno del servidor";
            jsonRespuesta = "{\"ok\":false,\"error\":\"" + mensajeError + "\"}";
        }

        try (PrintWriter out = response.getWriter()) {
            out.print(jsonRespuesta);
        }
    }
}
