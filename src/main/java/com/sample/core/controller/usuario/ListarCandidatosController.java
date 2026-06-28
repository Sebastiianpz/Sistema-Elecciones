package com.sample.core.controller.usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.sample.core.domain.Usuario;
import com.sample.core.service.UsuarioService;
import com.sample.core.service.UsuarioServiceImp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listarCandidatos")
public class ListarCandidatosController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioService usuarioService = new UsuarioServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        try {
            List<Usuario> lista = usuarioService.listCandidatos();

            out.print("[");
            for (int i = 0; i < lista.size(); i++) {
                Usuario u = lista.get(i);
                out.print("{");
                out.print("\"id\":"               + u.getId()             + ",");
                out.print("\"nombreCompleto\":\"" + u.getNombreCompleto() + "\",");
                out.print("\"partido\":\""        + u.getPartido()        + "\",");
                out.print("\"colorPartido\":\""   + u.getColorPartido()   + "\",");
                out.print("\"votos\":"            + u.getVotos());
                out.print("}");
                if (i < lista.size() - 1) out.print(",");
            }
            out.print("]");

        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"ok\":false,\"error\":\"Error al listar candidatos.\"}");
        }

        out.flush();
    }
    
}