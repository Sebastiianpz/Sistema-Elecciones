package com.sample.core.controller;

import java.io.IOException;
import java.time.LocalDate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.sample.core.domain.Persona;
import com.sample.core.enums.SexoEnum;
import com.sample.core.service.PersonaService;
import com.sample.core.service.PersonaServiceImp;

@WebServlet("/Modificar") // Asegurate de que no diga "/ModificarServlet" ni tenga barras duplicadas
public class ModificarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PersonaService personaService = new PersonaServiceImp();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            Persona p = new Persona();
            p.setNroDocumento(request.getParameter("nroDocumento"));
            p.setApellido(request.getParameter("apellido"));
            p.setNombre(request.getParameter("nombre"));
            p.setDomicilio(request.getParameter("domicilio"));
            p.setFechaNacimiento(LocalDate.parse(request.getParameter("fechaNacimiento")));
            p.setSexo(SexoEnum.valueOf(request.getParameter("sexo").toUpperCase()));
            p.setHabilitadoVotar(Boolean.parseBoolean(request.getParameter("habilitadoVotar")));

            personaService.modificarPersona(p);
            response.setStatus(HttpServletResponse.SC_OK); // 200 OK
        } catch (Exception e) {
            e.printStackTrace(); // Esto te va a mostrar el error exacto en la consola de Eclipse/Tomcat
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Error
        }
    }
}
