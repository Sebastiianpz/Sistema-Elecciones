package com.sample.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sample.core.dao.config.Conexion;
import com.sample.core.domain.Persona;
import com.sample.core.exceptions.ErrorException;

public class PersonaDaoImp implements PersonaDao {
	
	private static final String queryConsultarPadron = "SELECT id, nro_documento, habilitado_votar, ya_voto FROM persona WHERE id=?";

		private static final String queryAddPadron = "INSERT INTO padron (nro_documento, habilitado_votar, ya_voto) VALUES (?,?,?)";
		
		private static final String queryDeletePadron = "DELETE FROM persona WHERE id=?";
		
		private static final String queryList = "SELECT id, nro_documento, habilitado_votar, ya_voto FROM persona";
	
		private static final String queryUpdatePadron = "UPDATE persona SET nro_documento=?, habilitado_votar=?, ya_voto=? WHERE id=?";
		
	private Conexion conexion = Conexion.getInstance();


	public Persona findById(int id) throws Exception {
		 ResultSet rs = null;
		 PreparedStatement st = null;
		 try{
			st = conexion.dameConnection().prepareStatement(queryConsultarPadron);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				return new Persona(rs.getInt("id"),
						rs.getString("nro_documento"),
					    rs.getBoolean("habilitado_votar"),
					    rs.getString("ya_voto")
					   ));
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

	public List<Persona> list() throws Exception {

		PreparedStatement st= null;
		ResultSet rs = null;
		List<Persona> personas = new ArrayList<Persona>();

		try {
			st = conexion.dameConnection().prepareStatement(queryList);
			rs = st.executeQuery();
			
			while (rs.next()) {
				personas.add(new Persona(
			    	rs.getInt("id"),
			        rs.getString("nro_documento"),
			        rs.getString("habilitado_votar"),
			        rs.getString("ya_voto")
			        
			    ));
			}
			
		} catch (Exception e) {
			System.out.println(e.getCause());
		}finally {
			st.close();
			rs.close();
		}
		
		return personas;
	}

	public void save(String nro_documento, boolean habilitado_votar, boolean ya_voto ) throws ErrorException {

		 ResultSet rs = null;
		 PreparedStatement st = null;
		 try{
			st = conexion.dameConnection().prepareStatement(queryAddPadron);
			st.setString(1, nro_documento);
	        st.setBoolean(2, habilitado_votar);
	        st.setBoolean(3, ya_voto);
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
		PreparedStatement st = this.conexion.dameConnection().prepareStatement(queryDeleteRodado);
		st.setInt(1, id);
		int registros = st.executeUpdate();
		
		if (registros==0) {
			throw new Exception("hubo un error ");
		}		
		st.close();
	}

	public void update(Rodado r) throws Exception {
	    PreparedStatement st = null;
	    try {
	        java.sql.Connection con = conexion.dameConnection();
	        
	        st = con.prepareStatement(queryUpdateRodado);
	        
	        st.setString(1, r.getPatente());
	        st.setString(2, r.getTipo().name());
	        st.setString(3, r.getMotor());
	        st.setString(4, r.getColor());
	        st.setString(5, r.getTipoCaja().name());
	        st.setString(6, r.getTipoConsumo().name());
	        st.setInt(7, r.getId()); 
	        
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

	

	@Override
	public void update(Persona p) throws Exception {
		// TODO Auto-generated method stub
		
	}



}
