package com.sample.core.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.sample.core.service.UsuarioService;
import com.sample.core.service.UsuarioServiceImp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UsuarioService usuarioService = new UsuarioServiceImp();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();

        // Validaciones
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            out.print("{");
            out.print("\"ok\":false,");
            out.print("\"error\":\"Debe completar usuario y contraseña.\"");
            out.print("}");
            out.flush();
            return;
        }

        try {

            boolean login = usuarioService.login(
                    username.trim(),
                    password.trim());

            if (login) {

                out.print("{");
                out.print("\"ok\":true");
                out.print("}");

            } else {

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                out.print("{");
                out.print("\"ok\":false,");
                out.print("\"error\":\"Usuario o contraseña incorrectos.\"");
                out.print("}");

            }

            out.flush();

        } catch (Exception e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            out.print("{");
            out.print("\"ok\":false,");
            out.print("\"error\":\"" + e.getMessage().replace("\"", "'") + "\"");
            out.print("}");

            out.flush();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
}