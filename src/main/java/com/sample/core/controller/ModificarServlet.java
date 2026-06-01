package ar.edu.padron.controller;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.padron.domain.Persona;
import ar.edu.padron.enums.SexoEnum;
import ar.edu.padron.service.PersonaService;
import ar.edu.padron.service.PersonaServiceImp;

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
