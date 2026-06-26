package com.sample.core.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String uri = request.getRequestURI();
        String ctx = request.getContextPath();

        // Recursos y páginas públicas (no requieren iniciar sesión)
        boolean esPublica =
                uri.equals(ctx + "/Login") ||
                uri.equals(ctx + "/Logout") ||

                uri.contains("/vistas/login.jsp") ||
                uri.contains("/vistas/busqueda.jsp") ||

                uri.equals(ctx + "/Busqueda") ||
                uri.equals(ctx + "/Imagen") ||

                uri.contains("/bootstrap/") ||
                uri.contains("/css/") ||
                uri.contains("/scripts/");

        if (esPublica) {
            chain.doFilter(request, response);
            return;
        }

        // Verificar si existe una sesión iniciada
        HttpSession session = request.getSession(false);
        boolean logueado = (session != null && session.getAttribute("usuarioId") != null);

        if (logueado) {
            chain.doFilter(request, response);
        } else {

            // Si la petición es AJAX, devolver 401
            String xhr = request.getHeader("X-Requested-With");

            if ("XMLHttpRequest".equals(xhr)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().print("{\"ok\":false,\"error\":\"Sesión expirada.\"}");
            } else {
                // Si es una navegación normal, volver al login
                response.sendRedirect(ctx + "/vistas/login.jsp");
            }
        }
    }

    @Override
    public void destroy() {
    }
}