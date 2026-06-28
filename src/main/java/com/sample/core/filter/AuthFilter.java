package com.sample.core.filter;

import java.io.IOException;
// Importaciones migradas a Jakarta
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
        System.out.println("AUTH FILTER INICIALIZADO");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("AUTH FILTER EJECUTANDO");

        HttpServletRequest  request  = (HttpServletRequest)  req;
        HttpServletResponse response = (HttpServletResponse) res;

        String uri = request.getRequestURI();
        String ctx = request.getContextPath();

        // Rutas públicas — no requieren sesión
        boolean esPublica =
            // Auth
            uri.equals(ctx + "/Login")                          ||
            uri.equals(ctx + "/Logout")                         ||
            uri.equals(ctx + "/loginAdministrador")             ||
            // Páginas JSP públicas
            uri.contains("/login-admin/login-admin.jsp")        ||
            uri.contains("/home/home.jsp")                      ||
            uri.contains("/habilitado-administrador")           ||
            uri.contains("/habilitado-ciudadano")               ||
            uri.contains("/votacion/")                          ||
            uri.contains("/confirmacion/")                      ||
            uri.contains("/no-existe/")                         ||
            uri.contains("/ya-voto/")                           ||
            uri.contains("/no-habilitado/no-habilitado.jsp/")   ||
            // Servlets del votante (públicos)
            uri.equals(ctx + "/ingresarMesa")                   ||
            uri.equals(ctx + "/dashboard-stats")                ||
            uri.equals(ctx + "/dashboard-resultados")           ||
            uri.equals(ctx + "/validarPersonaPorDNI")           ||
            uri.equals(ctx + "/votar")                          ||
            uri.equals(ctx + "/voto-confirmado")                ||
            uri.equals(ctx + "/listarCandidatos")               ||
            // Recursos estáticos
            uri.contains("/scripts/")                           ||
            uri.contains("/css/")                               ||
            uri.contains("/images/")                            ||
            uri.endsWith(".js")                                 ||
            uri.equals(ctx + "/guardarVoto")                    ||
            uri.endsWith(".css")                                ||
            uri.endsWith(".png")                                ||
            uri.endsWith(".jpg");
            
        if (esPublica) {
            chain.doFilter(request, response);
            return;
        }

        // Verificar sesión activa
        HttpSession session = request.getSession(false);
        boolean logueado = (session != null && session.getAttribute("usuarioId") != null);

        if (logueado) {
            chain.doFilter(request, response);
        } else {
            // AJAX → responde 401 JSON; navegación normal → redirige al login
            String xhr = request.getHeader("X-Requested-With");
            if ("XMLHttpRequest".equals(xhr)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().print("{\"ok\":false,\"error\":\"Sesion expirada.\"}");
            } else {
                response.sendRedirect(ctx + "/home/home.jsp");
            }
        }
    }

    @Override
    public void destroy() {}
}