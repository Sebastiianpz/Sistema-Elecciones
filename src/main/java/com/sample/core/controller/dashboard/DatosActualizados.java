package com.sample.core.controller.dashboard;


import com.sample.core.service.DashboardService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// URL: GET /dashboard-stats
// Responde con JSON de métricas generales para el dashboard
@WebServlet("/dashboard-stats")
public class DatosActualizados extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Permite que el JSP llame desde el mismo servidor sin problemas de CORS
        response.setHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = response.getWriter();

        try {
            DashboardService service = new DashboardService();

            int    totalPadron    = service.getTotalPadron();
            int    habilitados    = service.getHabilitados();
            int    votosEmitidos  = service.getVotosEmitidos();
            int    deshabilitados = service.getDeshabilitados();
            int    equiposActivos = service.getEquiposActivos();
            double participacion  = service.getParticipacion();

            // Construir JSON manualmente (sin librería externa)
            String json = "{"
                + "\"totalPadron\":"    + totalPadron    + ","
                + "\"habilitados\":"    + habilitados    + ","
                + "\"votosEmitidos\":"  + votosEmitidos  + ","
                + "\"deshabilitados\":" + deshabilitados + ","
                + "\"equiposActivos\":" + equiposActivos + ","
                + "\"participacion\":"  + participacion
                + "}";

            out.print(json);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
            e.printStackTrace();
        }

        out.flush();
    }
}