package com.sample.core.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet( urlPatterns =  "/formulario")
public class LoadForm extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String LOGIN_FORM ="usuario/login.jsp";
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session =   req.getSession(true);	

		if (req.getParameter("form").equals("usuarioForm") && session.getAttribute("CURRENT_USER") != null) {
			req.getRequestDispatcher(LOGIN_FORM).forward(req, resp);
		}
	}
	
}
