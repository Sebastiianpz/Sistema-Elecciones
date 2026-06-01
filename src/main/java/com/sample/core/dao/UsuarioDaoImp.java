package ar.edu.padron.dao;

import ar.edu.padron.db.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSet;
import java.sql.Statement;

import ar.edu.padron.domain.Usuario;

public class UsuarioDaoImp implements UsuarioDAO {

	@Override
	public Usuario findByUsername(String user) throws Exception {
	    Usuario u = null;
	    Statement st = null;
	    ResultSet rs = null;
	    try {
	        st = ConexionDB.getInstance().dameConnection().createStatement();
	        rs = st.executeQuery("SELECT * FROM usuarios WHERE username = '" + user + "'");
	        
	        if (rs.next()) {
	            u = new Usuario();
	            u.setId(rs.getInt("id"));
	            u.setUsername(rs.getString("username"));
	            u.setPassword(rs.getString("password"));
	        }

	        return u;
	    } catch (Exception e) {
	        System.out.println("Error en DAO al buscar por usuario: " + e.getMessage());
	        throw new Exception("Error al obtener el usuario: " + e.getMessage());
	    } finally {
	        finalizarConexion(st, rs);
	    }
	}
@Override
public boolean validarCredenciales(String user, String pass) throws Exception {
    Usuario u = findByUsername(user);
    
    // Si el usuario existe y la contraseña coincide, retorna true
    if (u != null && u.getPassword().equals(pass)) {
        return true;
    }
    
    return false;
}

	private void finalizarConexion(Statement st, ResultSet rs) {
		try {
			if (st != null) {
				st.close();
			}
			if (rs != null) { // Esta validaciï¿½n evita el NullPointerException
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println("Error al cerrar recursos: " + e.getMessage());
		}
	}
}
