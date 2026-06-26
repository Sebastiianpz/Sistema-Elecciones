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

@WebServlet("/listarCandidatos")
public class ListarCandidatosController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioService usuarioService = new UsuarioServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // 1. Obtenemos la lista desde tu interfaz real
            List<Usuario> lista = usuarioService.listCandidatos();
            
            // 2. Empezamos a armar el JSON de la lista abriendo corchetes
            out.print("[");
            
            for (int i = 0; i < lista.size(); i++) {
                Usuario u = lista.get(i);
                
                out.print("{");
                out.print("\"id\":" + u.getId() + ",");
                
                // OJO: Si en tu domain 'Usuario' el método se llama getNombre() 
                // o getUsername(), recordá cambiarlo acá para que coincida:
                out.print("\"nombreCompleto\":\"" + u.getNombreCompleto() + "\""); 
                // Si necesitás agregar más datos como el partido político podés sumarlos acá con una coma intermedia.
                
                out.print("}");
                
                // Si NO es el último elemento de la lista, le metemos una coma para separar los objetos
                if (i < lista.size() - 1) {
                    out.print(",");
                }
            }
            
            out.print("]"); // Cerramos la lista JSON
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            
            try (PrintWriter out = response.getWriter()) {
                out.print("{");
                out.print("\"ok\":false,");
                out.print("\"error\":\"Error al listar los candidatos: " + e.getMessage().replace("\"", "'") + "\"");
                out.print("}");
            }
        }
    }
}