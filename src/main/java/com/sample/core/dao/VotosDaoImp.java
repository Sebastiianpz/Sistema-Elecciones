package com.sample.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sample.core.dao.config.Conexion;
import com.sample.core.domain.Persona;
import com.sample.core.domain.Voto;
import com.sample.core.exceptions.ErrorException;

public class VotosDaoImp implements VotosDao {
	
	private static final String queryList = "SELECT id, persona_id, candidato_id, pc_id, fecha_voto FROM votos";
		
	private static final String queryAddVoto = "INSERT INTO votos (persona_id, candidato_id, pc_id, fecha_voto) VALUES (?, ?, ?, NOW())";
	
	private static final String querySumarVotoCandidato = "UPDATE candidatos SET votos = votos + 1 WHERE id = ?";
	
	private static final String queryBloquearPersona = "UPDATE personas SET ya_voto = 1 WHERE id = ?";
	
	private Conexion conexion = Conexion.getInstance();

	
	public List<Voto> list() throws Exception {
		PreparedStatement st= null;
		ResultSet rs = null;
		List<Voto> votos = new ArrayList<Voto>();

		try {
			st = conexion.dameConnection().prepareStatement(queryList);
			rs = st.executeQuery();
			
			while (rs.next()) {
				votos.add(new Voto(
			    	rs.getInt("id"),
			    	rs.getInt("persona_id"),
				    rs.getInt("candidato_id"),
				    rs.getInt("pc_id"),
				    rs.getDate("fecha_voto")
			    ));
			}
			
		} catch (Exception e) {
			System.out.println(e.getCause());
		}finally {
			st.close();
			rs.close();
		}
		
		return votos;
	}

	public void save(int personaId, int candidatoId, int pcId) throws Exception {
		 ResultSet rs = null;
		 PreparedStatement stvoto = null;
		 PreparedStatement stcandidato = null;
		 PreparedStatement stpersona = null;

		 try{
			 stvoto = conexion.dameConnection().prepareStatement(queryAddVoto);
			stvoto.setInt(1, personaId);
			stvoto.setInt(2, candidatoId);
			stvoto.setInt(3, pcId);
			stvoto.executeUpdate();
	        
			stcandidato = conexion.dameConnection().prepareStatement(querySumarVotoCandidato);
			stcandidato.setInt(1, candidatoId);
			stcandidato.executeUpdate();
			
			stpersona = conexion.dameConnection().prepareStatement(queryBloquearPersona);
	        stpersona.setInt(1, personaId); 
	        stpersona.executeUpdate();
	        
		 }catch (Exception e) {
				throw new ErrorException("Hubo un error al realizar el voto", e);
		}finally {
			try {
				if (stvoto != null) stvoto.close();
	            if (stcandidato != null) stcandidato.close();
	            if (stpersona != null) stpersona.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}				
	}

}
