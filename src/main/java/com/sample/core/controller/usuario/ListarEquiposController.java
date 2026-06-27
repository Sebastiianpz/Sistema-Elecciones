package com.sample.core.controller.usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.core.domain.Usuario;
import com.sample.core.service.UsuarioService;
import com.sample.core.service.UsuarioServiceImp;

@WebServlet("/listarEquipos")
public class ListarEquiposController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioService usuarioService = new UsuarioServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            List<Usuario> lista = usuarioService.listEquipos();
            
            out.print("[");
            for (int i = 0; i < lista.size(); i++) {
                Usuario e = lista.get(i);
                
                // Validamos que los campos de texto no sean nulos reales en Java antes de enviar
                String nombre = (e.getNombreMac() != null) ? e.getNombreMac() : "PC Sin Nombre";
                String mac = (e.getMacAddress() != null) ? e.getMacAddress() : "00-00-00-00-00-00";
                
                out.print("{");
                out.print("\"id\":" + e.getId() + ",");
                out.print("\"nombreMac\":\"" + nombre + "\","); // <-- Se agregó la coma limpia
                out.print("\"macAddress\":\"" + mac + "\",");   // <-- Se agregó la coma limpia
                out.print("\"votosEmitidos\":" + e.getVotosEmitidos()); // <-- Sin comillas al final porque es INT
                out.print("}");
                
                if (i < lista.size() - 1) {
                    out.print(",");
                }
            }
            out.print("]");
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try (PrintWriter out = response.getWriter()) {
                out.print("{\"ok\":false,\"error\":\"Error al listar equipos.\"}");
            }
        }
    }
}