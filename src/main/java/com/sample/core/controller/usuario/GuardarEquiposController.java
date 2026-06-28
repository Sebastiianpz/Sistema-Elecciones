package com.sample.core.controller.usuario;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

import com.sample.core.service.UsuarioService;
import com.sample.core.service.UsuarioServiceImp;

@WebServlet("/guardarEquipo")
public class GuardarEquiposController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioService usuarioService = new UsuarioServiceImp();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String macAddress = request.getParameter("macAddress");
            String nombreMac = request.getParameter("nombreMac");
            boolean estadoPc = Boolean.parseBoolean(request.getParameter("estadoPc"));
            int votosEmitidos = Integer.parseInt(request.getParameter("votosEmitidos"));

            usuarioService.saveEquipos(macAddress, nombreMac, estadoPc, votosEmitidos);

            out.print("{\"ok\":true,\"mensaje\":\"Equipo registrado correctamente.\"}");
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try (PrintWriter out = response.getWriter()) {
                out.print("{\"ok\":false,\"error\":\"Error al guardar el equipo.\"}");
            }
        }
    }
}