package com.sample.core.controller.usuario;

	import java.io.IOException;
	import java.net.InetAddress;
	import java.net.NetworkInterface;
	import java.util.List;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	import com.sample.core.domain.Usuario; 
	import com.sample.core.service.UsuarioService;
	import com.sample.core.service.UsuarioServiceImp;

	@WebServlet("/ingresarMesa")
public class ValidarEquipoController {

	    private static final long serialVersionUID = 1L;
	    private UsuarioService usuarioService = new UsuarioServiceImp();

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try {
	            String macActual = obtenerMacDeEstaPc();
	            System.out.println(" PC intentando entrar con MAC: " + macActual);

	            // 2. Buscamos en la lista de equipos habilitados de la base de datos
	            List<Usuario> equipos = usuarioService.listEquipos();
	            Usuario pcEncontrada = null;

	            for (Usuario pc : equipos) {
	                // Suponiendo que guardas la MAC en algun campo (ej: e.getDni() o el campo que usen para la MAC)
	                // Reemplazá e.getDni() por el getter real de la MAC address en tu objeto
	                if (pc.getMacAddress() != null && pc.getMacAddress().equalsIgnoreCase(macActual)) {
	                    pcEncontrada = pc;
	                    break;
	                }
	            }

	            // 3. Evaluamos las condiciones de bloqueo
	         // ... (Todo el código donde busca la MAC y evalúa si existe) ...

	            if (pcEncontrada == null) {
	                response.getWriter().print("{\"permitido\":false,\"mensajeError\":\"Este equipo no está registrado para el padrón electoral.\"}");
	                return;
	            }

	            if (!estaHabilitada) {
	                response.getWriter().print("{\"permitido\":false,\"mensajeError\":\"MESA BLOQUEADA: Terminal deshabilitada por el administrador.\"}");
	                return;
	            }

	            // Si está todo bien, guardamos el ID real en la sesión y habilitamos el paso
	            request.getSession().setAttribute("idPcMesa", pcEncontrada.getId());
	            response.getWriter().print("{\"permitido\":true}");

	        } catch (Exception e) {
	            e.printStackTrace();
	            response.sendRedirect(request.getContextPath() + "/error.jsp");
	        }
	    }

	    private String obtenerMacDeEstaPc() throws Exception {
	        InetAddress ip = InetAddress.getLocalHost();
	        NetworkInterface network = NetworkInterface.getByInetAddress(ip);
	        if (network == null) return "00-00-00-00-00-00";
	        
	        byte[] mac = network.getHardwareAddress();
	        if (mac == null) return "00-00-00-00-00-00";

	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < mac.length; i++) {
	            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
	        }
	        return sb.toString(); // 
	    }
	
}
