package com.sample.core.controller.persona;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sample.core.domain.Persona;
import com.sample.core.service.PersonaService;
import com.sample.core.service.PersonaServiceImp;

@WebServlet("/validarPersonaPorDNI") 
public class ValidarPersonaDniController extends HttpServlet {

    private PersonaService personaService = new PersonaServiceImp();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1. Agarramos el DNI enviado por el AJAX
            String dniIngresado = request.getParameter("documento");
            
            // 2. Buscamos la persona a través del servicio (va al DAO)
            Persona dniPersona = personaService.buscarPorDocumento(dniIngresado);
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (dniPersona != null) {
                // 3. ¡RESCATAMOS el nombre completo antes de sobreescribir el rol!
                String nombreYApellido = dniPersona.getRol(); 

                // 4. Evaluamos el rol real por DNI
                List<String> dnisAdmins = Arrays.asList(
                    "48318477", "48854564", "48526185", "48318493", "48388651", "48114997"
                );

                if (dnisAdmins.contains(dniPersona.getNroDocumento())) {
                    dniPersona.setRol("ADMIN");
                } else {
                    dniPersona.setRol("CIUDADANO");
                }

                // 5. Armamos el mapa con los datos exactos que necesita el JS
                Map<String, Object> jsonMap = new HashMap<>();
                jsonMap.put("id", dniPersona.getId());
                jsonMap.put("nroDocumento", dniPersona.getNroDocumento());
                jsonMap.put("habilitadoVotar", dniPersona.isHabilitadoVotar()); 
                jsonMap.put("yaVoto", dniPersona.isYaVoto());                  
                jsonMap.put("rol", dniPersona.getRol());
                jsonMap.put("nombreCompleto", nombreYApellido); // 👈 Mandamos el nombre suelto en el JSON

                // 6. Convertimos el mapa a JSON string y lo respondemos
                String json = new Gson().toJson(jsonMap);
                response.getWriter().write(json);
            } else {
                response.getWriter().write("null");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}