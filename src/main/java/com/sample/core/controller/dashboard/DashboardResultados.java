package com.sample.core.controller.dashboard;

import com.sample.core.service.DashboardService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

// URL: GET /dashboard-resultados
// Responde con JSON array de candidatos con sus votos
@WebServlet("/dashboard-resultados")
public class DashboardResultados extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        try {
            DashboardService service = new DashboardService();
            List<Map<String, Object>> candidatos = service.getResultadosCandidatos();

            StringBuilder json = new StringBuilder("[");

            for (int i = 0; i < candidatos.size(); i++) {
                Map<String, Object> c = candidatos.get(i);

                // Escapar comillas por si el nombre tiene caracteres especiales
                String nombre  = c.get("nombre_completo").toString().replace("\"", "\\\"");
                String partido = c.get("partido").toString().replace("\"", "\\\"");
                String color   = c.get("color_partido") != null
                                    ? c.get("color_partido").toString()
                                    : "#1a56db";
                int votos = (int) c.get("votos");

                json.append("{")
                    .append("\"nombre_completo\":\"").append(nombre).append("\",")
                    .append("\"partido\":\"").append(partido).append("\",")
                    .append("\"color_partido\":\"").append(color).append("\",")
                    .append("\"votos\":").append(votos)
                    .append("}");

                if (i < candidatos.size() - 1) json.append(",");
            }

            json.append("]");
            out.print(json.toString());

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
        }

        out.flush();
    }
}
