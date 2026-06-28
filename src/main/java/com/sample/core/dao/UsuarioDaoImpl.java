package com.sample.core.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sample.core.dao.config.Conexion;
import com.sample.core.domain.Usuario;
import com.sample.core.exceptions.ErrorException;

public class UsuarioDaoImpl implements UsuarioDao {

	private static final String queryLogin = "SELECT id, username, password FROM usuarios WHERE username=? AND password=?";
	private static final String queryFindCandidato = "SELECT id, nombre_completo, partido, num_partido, color_partido, votos FROM candidatos WHERE id=?";
	private static final String queryListCandidato = "SELECT id, nombre_completo, partido, num_partido, color_partido, votos FROM candidatos";
	private static final String queryAddCandidato = "INSERT INTO candidatos (nombre_completo, partido, num_partido, color_partido, votos) VALUES (?, ?, ?, ?, 0)";	
	private static final String queryUpdateCandidato = "UPDATE candidatos SET nombre_completo=?, partido=?, color_partido=? WHERE id=?";
	private static final String queryDeleteCandidato = "DELETE FROM candidatos WHERE id=?";
	private static final String queryFindEquipo = "SELECT id, mac_address, nombre, habilitada, votos_emitidos, usuario_id FROM pcs_habilitadas WHERE id=?";
	private static final String queryListEquipo = "SELECT id, mac_address, nombre, habilitada, votos_emitidos, usuario_id FROM pcs_habilitadas";
	private static final String queryAddEquipo = "INSERT INTO pcs_habilitadas (mac_address, nombre, habilitada, votos_emitidos, usuario_id) VALUES (?, ?, ?, 0, NOW(), ?)";
	private static final String queryDeleteMac = "DELETE FROM pcs_habilitadas WHERE id=?";
	private static final String queryUpdateEquipos = "UPDATE pcs_habilitadas SET mac_address =?, nombre =?, habilitada =? WHERE id=?";

	
	private Conexion conexion = Conexion.getInstance();

	public Usuario loginAdmin(String usuario, String contrasena) throws Exception {
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    Usuario adminLogueado = null;
	    
	    try {
	        st = this.conexion.dameConnection().prepareStatement(queryLogin);
	        st.setString(1, usuario);
	        st.setString(2, contrasena);
	        rs = st.executeQuery();

	        if (rs.next()) {
	            adminLogueado = new Usuario();
	            adminLogueado.setId(rs.getInt("id"));
	            adminLogueado.setNombreCompleto(rs.getString("username"));
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new Exception("Error en la consulta de login: " + e.getMessage());
	    } finally {
	        if (rs != null) rs.close();
	        if (st != null) st.close();
	    }
	    return adminLogueado;
	}

	public Usuario findCandidatoByid(int id) throws Exception {
		 ResultSet rs = null;
		 PreparedStatement st = null;
		 try{
			st = conexion.dameConnection().prepareStatement(queryFindCandidato);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
			    Usuario candidato = new Usuario();
			    
			    candidato.setId(rs.getInt("id"));
			    candidato.setNombreCompleto(rs.getString("nombre_completo"));
			    candidato.setPartido(rs.getString("partido"));
			    candidato.setNumPartido(rs.getInt("num_partido")); 
			    candidato.setColorPartido(rs.getString("color_partido"));
			    candidato.setVotos(rs.getInt("votos"));
			    
			    return candidato;
			}

		 }catch (Exception e) {
				throw new ErrorException("Hubo un error al realizar la consulta", e);
		}finally {
			try {
				st.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
	}

	public List<Usuario> listCandidatos() throws Exception {

	    PreparedStatement st = null;
	    ResultSet rs = null;
	    List<Usuario> candidatos = new ArrayList<Usuario>();

	    try {
	        st = conexion.dameConnection().prepareStatement(queryListCandidato);
	        rs = st.executeQuery();
	        
	        while (rs.next()) {
	            Usuario candidato = new Usuario();
	            
	            candidato.setId(rs.getInt("id"));
	            candidato.setNombreCompleto(rs.getString("nombre_completo"));
	            candidato.setPartido(rs.getString("partido"));
	            candidato.setNumPartido(rs.getInt("num_partido")); 
	            candidato.setColorPartido(rs.getString("color_partido"));
	            candidato.setVotos(rs.getInt("votos"));
	        
	            candidatos.add(candidato); // ✅ FIX: se agrega el candidato a la lista
	        }
	        
	    }  catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("ERROR DETALLADO: " + e.getMessage());
	    } finally {
	        if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
	        if (st != null) try { st.close(); } catch (SQLException e) { e.printStackTrace(); }
	    }
	    
	    return candidatos;
	}
	
	public void saveCandidato(String nombreCompleto, String partido, int numPartido, String colorPartido) throws ErrorException {

		 ResultSet rs = null;
		 PreparedStatement st = null;
		 try{
			st = conexion.dameConnection().prepareStatement(queryAddCandidato);
			st.setString(1, nombreCompleto);
	        st.setString(2, partido);
	        st.setInt(3, numPartido);
	        st.setString(4, colorPartido);
	        st.executeUpdate();
		 }catch (Exception e) {
				throw new ErrorException("Hubo un error al realizar la consulta", e);
		}finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	public void updateCandidato(Usuario can) throws Exception {
	    PreparedStatement st = null;
	    try {
	        java.sql.Connection con = conexion.dameConnection();
	        
	        st = con.prepareStatement(queryUpdateCandidato);
	        
	        st.setString(1, can.getNombreCompleto());
	        st.setString(2, can.getPartido());
	        st.setString(3, can.getColorPartido());  // ← sin numPartido
	        st.setInt(4, can.getId());
	        
	        int filasAfectadas = st.executeUpdate();
	        
	        if (!con.getAutoCommit()) {
	            con.commit();
	        }
	        
	    } catch (Exception e) {
	        try {
	            conexion.dameConnection().rollback();
	        } catch (Exception ex) { }
	        
	        e.printStackTrace();
	        throw new Exception("Error al actualizar en BD: " + e.getMessage());
	    } finally {
	        if (st != null) st.close();
	    }
	}
	
	public void deleteCandidato(int id) throws Exception {
	    PreparedStatement st = this.conexion.dameConnection().prepareStatement(queryDeleteCandidato);
	    st.setInt(1, id);
	    int registros = st.executeUpdate();
	    
	    if (registros == 0) {
	        throw new Exception("hubo un error");
	    }
	    st.close();
	}
	
	public Usuario findEquipoByid(int id) throws Exception {
	     ResultSet rs = null;
	     PreparedStatement st = null;
	     try {
	        st = conexion.dameConnection().prepareStatement(queryFindEquipo);
	        st.setInt(1, id);
	        rs = st.executeQuery();
	        
	        if (rs.next()) {
	            Usuario equipo = new Usuario();
	            equipo.setId(rs.getInt("id"));     
	            equipo.setMacAddress(rs.getString("mac_address"));  
	            equipo.setNombreMac(rs.getString("nombre")); 
	            equipo.setEstadoPc(rs.getBoolean("habilitada"));
	            equipo.setVotos(rs.getInt("votos_emitidos"));
	            
	            return equipo; 
	        }

	     } catch (Exception e) {
	         throw new ErrorException("Hubo un error al realizar la consulta", e);
	     } finally {
	         try {
	             if (st != null) st.close();
	             if (rs != null) rs.close();
	         } catch (SQLException e) {
	             e.printStackTrace();
	         }
	     }
	     return null; 
	}
	
	public List<Usuario> listEquipos() throws Exception {

	    PreparedStatement st = null;
	    ResultSet rs = null;
	    List<Usuario> gestionDeEquipos = new ArrayList<Usuario>();

	    try {
	        st = conexion.dameConnection().prepareStatement(queryListEquipo);
	        rs = st.executeQuery();
	        
	        while (rs.next()) {
	            Usuario gestionDeEquipo = new Usuario();
	            
	            gestionDeEquipo.setId(rs.getInt("id"));     
	            gestionDeEquipo.setMacAddress(rs.getString("mac_address"));  
	            gestionDeEquipo.setNombreMac(rs.getString("nombre"));
	            gestionDeEquipo.setEstadoPc(rs.getBoolean("habilitada"));
	            gestionDeEquipo.setVotos(rs.getInt("votos_emitidos"));
	            
	            gestionDeEquipos.add(gestionDeEquipo); // ✅ FIX: se agrega el equipo a la lista
	        }
	        
	    } catch (Exception e) {
	        System.out.println(e.getCause());
	    } finally {
	        if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
	        if (st != null) try { st.close(); } catch (SQLException e) { e.printStackTrace(); }
	    }
	    
	    return gestionDeEquipos;
	}
	
	public void saveEquipos(String macAddress, String nombreMac, boolean estadoPc, int votosEmitidos) throws ErrorException {

		 ResultSet rs = null;
		 PreparedStatement st = null;
		 try{
			st = conexion.dameConnection().prepareStatement(queryAddEquipo);
			st.setString(1, macAddress);
	        st.setString(2, nombreMac);
	        st.setBoolean(3, estadoPc);
	        st.setInt(4, votosEmitidos);
	        st.executeUpdate();
		 }catch (Exception e) {
				throw new ErrorException("Hubo un error al realizar la consulta", e);
		}finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	public void deleteEquipos(int id) throws Exception {
		PreparedStatement st = this.conexion.dameConnection().prepareStatement(queryDeleteMac);
		st.setInt(1, id);
		int registros = st.executeUpdate();
		
		if (registros==0) {
			throw new Exception("hubo un error ");
		}		
		st.close();
	}
	
	public void cambiarEstadoEquipos(Usuario mac) throws Exception {
	    PreparedStatement st = null;
	    try {
	        java.sql.Connection con = conexion.dameConnection();
	        
	        st = con.prepareStatement(queryUpdateEquipos);
	        
	        st.setString(1, mac.getMacAddress());
	        st.setString(2, mac.getNombreMac());
	        st.setBoolean(3, mac.isEstadoPc());
	        st.setInt(4, mac.getId()); 
	        
	        int filasAfectadas = st.executeUpdate();
	        
	        if (!con.getAutoCommit()) {
	            con.commit();
	        }
	        
	    } catch (Exception e) {
	        try {
	            conexion.dameConnection().rollback();
	        } catch (Exception ex) { }
	        
	        e.printStackTrace();
	        throw new Exception("Error al actualizar en BD: " + e.getMessage());
	    } finally {
	        if (st != null) st.close();
	    }
	}
}
