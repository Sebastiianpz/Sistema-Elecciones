package com.sample.core.controller.candidato;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet( urlPatterns =  "/candidato")

public class CandidatoController {
		
		CandidatoService candidatoService = new CansidatosServiceImpl();
		
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		int id = Integer.parseInt(req.getParameter("id"));
		String partido=(String)	req.getAttribute("partido");
		String colorPartido = req.getParameter("colorPartido");
		int votos = Integer.parseInt(req.getParameter("votos"));
		
		try {
			candidatoService.crearCandidato(id, partido, colorPartido, votos);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/LeerDatosCandidatos");
	        dispatcher.forward(req, resp);
	        
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		
		
		
	}
