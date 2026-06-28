package com.sample.core.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {System.out.println("AUTH FILTER INICIALIZADO");}

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
        	    uri.contains("/votacion/")                          ||
        	    uri.contains("/confirmacion/")                      ||
        	    // Servlets del votante (públicos)
        	    uri.equals(ctx + "/dashboard-stats")        ||
        	    uri.equals(ctx + "/dashboard-resultados")   ||
        	    uri.equals(ctx + "/validarPersonaPorDNI")           ||
        	    uri.equals(ctx + "/votar")                          ||
        	    uri.equals(ctx + "/voto-confirmado")                ||
        	    // Recursos estáticos
        	    uri.contains("/scripts/")                           ||
        	    uri.contains("/css/")                               ||
        	    uri.contains("/images/")                            ||
        	    uri.endsWith(".js")                                 ||
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