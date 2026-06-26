package com.sample.core.controller.persona;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
	            // 1. Agarramos el DNI que mandaste en la "valija" de tu AJAX
	            String dniIngresado = request.getParameter("documento");
	            
	            // 2. Llamamos al nuevo método del Service para buscarlo
	            Persona dniPersona = personaService.buscarPorDocumento(dniIngresado);
	            
	            // 3. ¡Acá va tu bloque de los 7 DNIs mágicos!
	            if (dniPersona != null) {
	                List<String> dnisAdmins = Arrays.asList(
	                    "48318477", "48854564", "48526185", "48318493", "48388651", "48114997"
	                );

	                if (dnisAdmins.contains(dniPersona.getNroDocumento())) {
	                	dniPersona.setRol("ADMIN");
	                } else {
	                	dniPersona.setRol("CIUDADANO");
	                }
	            }
	            
	            // 4. Convertimos a JSON y respondemos al AJAX
	            String json = new Gson().toJson(dniPersona);
	            response.setContentType("application/json");
	            response.setCharacterEncoding("UTF-8");
	            response.getWriter().write(json);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        }
	    }
	}