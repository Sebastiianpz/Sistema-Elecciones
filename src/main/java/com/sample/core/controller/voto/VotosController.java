package com.sample.core.controller.voto;

import java.io.IOException;
import java.util.List;

import com.sample.core.domain.Voto;
import com.sample.core.service.VotosService;
import com.sample.core.service.VotosServiceImp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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