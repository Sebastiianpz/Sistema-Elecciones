package com.sample.core.controller.usuario;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sample.core.domain.Usuario;
import com.sample.core.service.UsuarioService;
import com.sample.core.service.UsuarioServiceImp;


@WebServlet("/listarCandidatos")
public class ListarCandidatosController extends HttpServlet {
<<<<<<< HEAD
    private static final long serialVersionUID = 1L;
    private UsuarioService usuarioService = new UsuarioServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        try {
            List<Usuario> lista = usuarioService.listCandidatos();

            out.print("[");
            for (int i = 0; i < lista.size(); i++) {
                Usuario u = lista.get(i);
                out.print("{");
                out.print("\"id\":"               + u.getId()             + ",");
                out.print("\"nombreCompleto\":\"" + u.getNombreCompleto() + "\",");
                out.print("\"partido\":\""        + u.getPartido()        + "\",");
                out.print("\"colorPartido\":\""   + u.getColorPartido()   + "\",");
                out.print("\"votos\":"            + u.getVotos());
                out.print("}");
                if (i < lista.size() - 1) out.print(",");
            }
            out.print("]");

        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"ok\":false,\"error\":\"Error al listar candidatos.\"}");
        }

        out.flush();
    }
    
}
=======
   private static final long serialVersionUID = 1L;
   private UsuarioService usuarioService = new UsuarioServiceImp();
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      
       response.setContentType("application/json");
       response.setCharacterEncoding("UTF-8");
      
       try {
           List<Usuario> lista = usuarioService.listCandidatos();
           String json = new Gson().toJson(lista);
           response.getWriter().write(json);
          
       } catch (Exception e) {
           e.printStackTrace();
           response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
       }
   }
}
>>>>>>> 2c5f7425c2d38609fc0d6f6055fb851fe8b8c43e
