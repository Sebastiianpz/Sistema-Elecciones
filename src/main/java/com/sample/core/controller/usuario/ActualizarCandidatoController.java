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

        // Usamos el try con recursos para el PrintWriter, igual al código del padrón
        try (PrintWriter out = response.getWriter()) {

            // 1. Levantamos los parámetros que envía el AJAX de tu compañera
            int id = Integer.parseInt(request.getParameter("id"));
            String nombreCompleto = request.getParameter("nombreCompleto");
            String partido = request.getParameter("partido");
            int numPartido = Integer.parseInt(request.getParameter("numPartido"));

            // 2. Armamos el objeto Usuario
            Usuario candidato = new Usuario();
            candidato.setId(id);
            // candidato.setNombreCompleto(nombreCompleto); <-- Usá tus setters reales

            // 3. Llamamos al servicio para guardar los cambios
            usuarioService.updateCandidato(candidato);

            // 4. Respondemos con JSON escrito a mano (tal cual tu ejemplo)
            out.print("{");
            out.print("\"ok\":true,");
            out.print("\"mensaje\":\"Candidato actualizado correctamente\"");
            out.print("}");

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            
            // Si algo falla, respondemos el JSON de error a mano
            try (PrintWriter out = response.getWriter()) {
                out.print("{");
                out.print("\"ok\":false,");
                out.print("\"error\":\"" + e.getMessage().replace("\"", "'") + "\"");
                out.print("}");
            }
        }
    }
}