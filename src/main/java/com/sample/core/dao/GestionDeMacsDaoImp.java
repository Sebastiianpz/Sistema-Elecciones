package com.sample.core.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sample.core.dao.config.Conexion;
import com.sample.core.domain.GestionDeMacs;
import com.sample.core.exceptions.ErrorException;

public class GestionDeMacsDaoImp implements GestionDeMacsDao {
	
	private static final String queryConsultarMac = "SELECT id, mac_address, nombre, habilitada, votos_emitidos, fecha_registro, usuario_id FROM pcs_habilitadas WHERE id=?";
	private static final String queryList = "SELECT id, mac_address, nombre, habilitada, votos_emitidos, fecha_registro, usuario_id FROM pcs_habilitadas";
	private static final String queryAddMac = "INSERT INTO pcs_habilitadas (mac_address, nombre, habilitada, votos_emitidos, fecha_registro, usuario_id) VALUES (?, ?, ?, 0, NOW(), ?)";	private static final String queryDeleteMac = "DELETE FROM pcs_habilitadas WHERE id=?";
	private static final String queryUpdateMac = "UPDATE pcs_habilitadas SET mac_address =?, nombre =?, habilitada =?, usuario_id =? WHERE id=?";
	
	private Conexion conexion = Conexion.getInstance();


	public GestionDeMacs findById(int id) throws Exception {
		 ResultSet rs = null;
		 PreparedStatement st = null;
		 try{
			st = conexion.dameConnection().prepareStatement(queryConsultarMac);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				return new GestionDeMacs(
						rs.getInt("id"),     
		                rs.getString("mac_address"),   
		                rs.getString("nombre"),
		                rs.getBoolean("habilitada"),
		                rs.getInt("votos_emitidos"),
		                rs.getDate("fecha_registro"),
						rs.getInt("usuario_id")  
		                );
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
	
	public List<GestionDeMacs> list() throws Exception {

		PreparedStatement st= null;
		ResultSet rs = null;
		List<GestionDeMacs> gestionDeMacs = new ArrayList<GestionDeMacs>();

		try {
			st = conexion.dameConnection().prepareStatement(queryList);
			rs = st.executeQuery();
			
			while (rs.next()) {
				gestionDeMacs.add(new GestionDeMacs(
						rs.getInt("id"),     
		                rs.getString("mac_address"),   
		                rs.getString("nombre"),
		                rs.getBoolean("habilitada"),
		                rs.getInt("votos_emitidos"),
		                rs.getDate("fecha_registro"),
						rs.getInt("usuario_id")  
			    ));
			}
			
		} catch (Exception e) {
			System.out.println(e.getCause());
		}finally {
			st.close();
			rs.close();
		}
		
		return gestionDeMacs;
	}
	
	public void save(String macAddress, String nombreMac, boolean estadoPc, int votosEmitidos, Date fechaRegistro, int usuarioId) throws ErrorException {

		 ResultSet rs = null;
		 PreparedStatement st = null;
		 try{
			st = conexion.dameConnection().prepareStatement(queryAddMac);
			st.setString(1, macAddress);
	        st.setString(2, nombreMac);
	        st.setBoolean(3, estadoPc);
	        st.setInt(4, votosEmitidos);
	        st.setDate(5, fechaRegistro);
	        st.setInt(6, usuarioId);
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

	public void delete(int id) throws Exception {
		PreparedStatement st = this.conexion.dameConnection().prepareStatement(queryDeleteMac);
		st.setInt(1, id);
		int registros = st.executeUpdate();
		
		if (registros==0) {
			throw new Exception("hubo un error ");
		}		
		st.close();
	}
	
	public void cambiarEstado(GestionDeMacs m) throws Exception {
	    PreparedStatement st = null;
	    try {
	        java.sql.Connection con = conexion.dameConnection();
	        
	        st = con.prepareStatement(queryUpdateMac);
	        
	        st.setString(1, m.getMacAddress());
	        st.setString(2, m.getNombreMac());
	        st.setBoolean(3, m.isEstadoPc());
	        st.setInt(4, m.getUsuarioId());
	        st.setInt(5, m.getId()); 
	        
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
