package com.sample.core.controller.candidato;

	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;

	import java.sql.Statement;

	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	import com.sample.core.service.CandidatoService;
	import com.sample.core.service.CandidatoServiceImpl;
	
		@WebServlet("/LeerDatosBebidas")
		
public class LeerCandidatosController extends HttpServlet{

		private CandidatoService candidatoService = new CandidatoServiceImpl();
		
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	
	    
	    	
	    	try {
				request.setAttribute("conjuntoResultados", candidatoService.listarCandidatos());
				request.getRequestDispatcher("/candidato/VerCandiato.jsp").forward(request, response);
	    	} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	}
