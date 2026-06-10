package com.sample.core.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.sample.core.domain.Persona;
import com.sample.core.service.PersonaService;
import com.sample.core.service.PersonaServiceImp;


@WebServlet("/Busqueda")
public class BusquedaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private PersonaService personaService = new PersonaServiceImp();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String dni = request.getParameter("dni");

        if (dni == null || dni.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter out = response.getWriter();
            out.print("{\"ok\":false,\"error\":\"Debe ingresar un número de documento.\"}");
            out.flush();
            return;
        }

        try {
            Persona p = personaService.buscarPorDocumento(dni.trim());

            PrintWriter out = response.getWriter();
            out.print("{");
            out.print("\"ok\":true,");
            out.print("\"nombre\":\"" + p.getNombre() + "\",");
            out.print("\"apellido\":\"" + p.getApellido() + "\",");
            out.print("\"dni\":\"" + p.getNroDocumento() + "\",");
            out.print("\"habilitado\":" + p.isHabilitadoVotar());
            out.print("}");
            out.flush();

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            PrintWriter out = response.getWriter();
            out.print("{\"ok\":false,\"error\":\"" + e.getMessage().replace("\"", "'") + "\"}");
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}