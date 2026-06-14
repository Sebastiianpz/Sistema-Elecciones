package com.sample.core.controller.voto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.core.service.VotosServiceImp;

@WebServlet("/listarVotos")
public class VotosController extends HttpServlet {

    private VotosService votosService = new VotosServiceImp();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1. Llamás al list() de tu interfaz
            List<Voto> listaDeVotos = votosService.list();
            
            // 2. Lo guardás en el request para que el JSP lo pueda dibujar
            request.setAttribute("listaVotos", listaDeVotos);
            
            // 3. Despachás al JSP correspondiente (ej: resultado.jsp)
            request.getRequestDispatcher("/resultado.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            // Redirigir a una página de error o setear estado
        }
    }
}