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
	
	private static final String queryConsultarPadron = "SELECT id, nro_documento, habilitado_votar, ya_voto FROM personas WHERE id=?";
				
		private static final String queryList = "SELECT id, nro_documento, habilitado_votar, ya_voto FROM personas";
	
		
	private Conexion conexion = Conexion.getInstance();


	public Persona findById(int id) throws Exception {
		 ResultSet rs = null;
		 PreparedStatement st = null;
		 try{
			st = conexion.dameConnection().prepareStatement(queryConsultarPadron);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				return new Persona(
						rs.getInt("id"),
						rs.getString("nro_documento"),
					    rs.getBoolean("habilitado_votar"),
					    rs.getBoolean("ya_voto")
					   );
			}

		 }catch (Exception e) {
				throw new ErrorException("Hubo un error al realizar la consulta", e);
		}finally {
			try {
				st.close();
				rs.close();
			} catch (SQLException e) {

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
			        rs.getBoolean("habilitado_votar"),
			        rs.getBoolean("ya_voto")
			        
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


	
	}





