package ar.edu.padron.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import ar.edu.padron.domain.Persona;
import ar.edu.padron.enums.SexoEnum;
import ar.edu.padron.service.PersonaService;
import ar.edu.padron.service.PersonaServiceImp;

@WebServlet("/Modificar")
public class ModificarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private PersonaService personaService = new PersonaServiceImp();

    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class,
                    (JsonSerializer<LocalDate>) (src, typeOfSrc, context) ->
                            new JsonPrimitive(src.toString()))
            .create();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {

            Persona p = new Persona();

            p.setNroDocumento(request.getParameter("nroDocumento"));
            p.setApellido(request.getParameter("apellido"));
            p.setNombre(request.getParameter("nombre"));
            p.setDomicilio(request.getParameter("domicilio"));
            p.setFechaNacimiento(LocalDate.parse(request.getParameter("fechaNacimiento")));
            p.setSexo(SexoEnum.valueOf(request.getParameter("sexo").toUpperCase()));

            personaService.modificarPersona(p);

            // respuesta JSON correcta
            out.print("{");
            out.print("\"ok\":true,");
            out.print("\"mensaje\":\"Persona modificada correctamente\"");
            out.print("}");

        } catch (Exception e) {

            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            try (PrintWriter out = response.getWriter()) {
                out.print("{");
                out.print("\"ok\":false,");
                out.print("\"error\":\"" + e.getMessage().replace("\"", "'") + "\"");
                out.print("}");
            }
        }
    }
}