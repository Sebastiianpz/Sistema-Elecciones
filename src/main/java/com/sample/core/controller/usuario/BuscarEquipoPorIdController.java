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

@WebServlet("/buscarEquipoPorId")
public class BuscarEquipoPorIdController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioService usuarioService = new UsuarioServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            int id = Integer.parseInt(request.getParameter("id"));

            Usuario equipo = usuarioService.findEquipoByid(id);

            if (equipo != null) {
                // Si arm�s el JSON manualmente, const�talo contra esto:
                out.print("{"
                    + "\"id\":" + equipo.getId() + ","
                    + "\"nombreMac\":\"" + equipo.getNombreMac() + "\","
                    + "\"macAddress\":\"" + equipo.getMacAddress() + "\""
                + "}");
            } else {
                out.print("null");
            }
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try (PrintWriter out = response.getWriter()) {
                out.print("{\"ok\":false,\"error\":\"Error al buscar el equipo.\"}");
            }
        }
    }
}
