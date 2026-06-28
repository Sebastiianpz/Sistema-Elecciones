package com.sample.core.controller.usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.sample.core.domain.Usuario;
import com.sample.core.service.UsuarioService;
import com.sample.core.service.UsuarioServiceImp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
                
                String nombre = (e.getNombreMac() != null) ? e.getNombreMac() : "PC Sin Nombre";
                String mac = (e.getMacAddress() != null) ? e.getMacAddress() : "00-00-00-00-00-00";
                
                out.print("{");
                out.print("\"id\":" + e.getId() + ",");
                out.print("\"nombreMac\":\"" + nombre + "\","); 
                out.print("\"macAddress\":\"" + mac + "\",");   
                
                out.print("\"estadoPc\":" + e.isEstadoPc() + ","); // <-- Mapea true o false directo
                
                out.print("\"votosEmitidos\":" + e.getVotos()); 
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