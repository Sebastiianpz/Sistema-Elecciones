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

@WebServlet("/modificarEquipo")
public class ActualizarDatosEquiposController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioService usuarioService = new UsuarioServiceImp();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String idParam    = request.getParameter("id");
            String nombreMac  = request.getParameter("nombreMac");
            String macAddress = request.getParameter("macAddress");

            if (idParam == null || idParam.trim().isEmpty()
                    || nombreMac == null || nombreMac.trim().isEmpty()
                    || macAddress == null || macAddress.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"ok\":false,\"error\":\"Faltan par�metros requeridos.\"}");
                out.flush();
                return;
            }

            Usuario equipoModificado = new Usuario();
            equipoModificado.setId(Integer.parseInt(idParam));
            equipoModificado.setNombreMac(nombreMac.trim());
            equipoModificado.setMacAddress(macAddress.trim());

            usuarioService.actualizarDatosEquipo(equipoModificado);

            out.print("{\"ok\":true,\"mensaje\":\"�Datos del equipo modificados con �xito!\"}");
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