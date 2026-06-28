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
                out.print("{\"ok\":false,\"error\":\"Faltan parámetros requeridos.\"}");
                out.flush();
                return;
            }

            Usuario equipoModificado = new Usuario();
            equipoModificado.setId(Integer.parseInt(idParam));
            equipoModificado.setNombreMac(nombreMac.trim());
            equipoModificado.setMacAddress(macAddress.trim());

            usuarioService.actualizarDatosEquipo(equipoModificado);

            out.print("{\"ok\":true,\"mensaje\":\"¡Datos del equipo modificados con éxito!\"}");
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