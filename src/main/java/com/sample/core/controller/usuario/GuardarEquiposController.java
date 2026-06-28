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

            if (macAddress == null || macAddress.trim().isEmpty() || 
                nombreMac == null || nombreMac.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"ok\":false,\"error\":\"Faltan campos obligatorios para registrar el equipo.\"}");
                out.flush();
                return;
            }

            boolean estadoPc = false; 
            int votosEmitidos = 0;    

            usuarioService.saveEquipos(macAddress.trim(), nombreMac.trim(), estadoPc, votosEmitidos);

            out.print("{\"ok\":true,\"mensaje\":\"Equipo registrado correctamente.\"}");
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try (PrintWriter out = response.getWriter()) {
                out.print("{\"ok\":false,\"error\":\"Error interno en el servidor al guardar el equipo: " + e.getMessage() + "\"}");
            }
        }
    }
}