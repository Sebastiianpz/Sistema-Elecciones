package com.sample.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.sample.core.dao.config.Conexion;
import com.sample.core.domain.Persona;
import com.sample.core.exceptions.ErrorException;

public class PersonaDaoImp implements PersonaDao {
	
	private static final String queryConsultarPadron = "SELECT id, nombre, apellido, nro_documento, habilitado_votar, ya_voto FROM personas WHERE id=?";
	private static final String queryBuscarDni = "SELECT id, nro_documento, habilitado_votar, ya_voto, nombre, apellido FROM personas WHERE nro_documento = ?";	
		
	private Conexion conexion = Conexion.getInstance();

	@Override
	public Persona findById(int id) throws Exception {
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			st = conexion.dameConnection().prepareStatement(queryConsultarPadron);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				// 1. Juntamos nombre y apellido de la base de datos
				String nombreCompleto = rs.getString("nombre") + " " + rs.getString("apellido");
				
				// 2. Lo pasamos en el último parámetro (donde tu constructor acepta un String)
				return new Persona(
						rs.getInt("id"),
						rs.getString("nro_documento"),
						rs.getBoolean("habilitado_votar"),
						rs.getBoolean("ya_voto"),
						nombreCompleto 
				);
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
	
	@Override
	public Persona findByDocumento(String dni) throws Exception {
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			st = conexion.dameConnection().prepareStatement(queryBuscarDni);
			st.setString(1, dni);
			rs = st.executeQuery();
			
			if (rs.next()) {
				// 1. Juntamos nombre y apellido de la base de datos
				String nombreCompleto = rs.getString("nombre") + " " + rs.getString("apellido");
				
				// 2. Creamos el objeto Persona usando ese String al final
				Persona p = new Persona(
						rs.getInt("id"),
						rs.getString("nro_documento"),
						rs.getBoolean("habilitado_votar"),
						rs.getBoolean("ya_voto"),
						nombreCompleto 
				);
				
				return p;
			}
		} catch (Exception e) {
			throw new ErrorException("Hubo un error al realizar la consulta por DNI", e);
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
}