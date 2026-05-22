package com.sample.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sample.core.dao.config.Conexion;
import com.sample.core.domain.Candidato;
import com.sample.core.exceptions.ErrorException;

public class CandidatoDaoImp {

	private static final String queryConsultarCandidato = "SELECT id, nombre_completo, partido, num_partido, color_partido FROM candidatos WHERE id=?";
	
	private static final String queryList = "SELECT id, nombre_completo, partido, num_partido, color_partido FROM candidatos";
	
	private static final String queryAddCandidato = "INSERT INTO candidatos (nombre_completo, partido, num_partido, color_partido, votos) VALUES (?, ?, ?, ?, 0)";	
	
	private static final String queryDeleteCandidato = "DELETE FROM candidatos WHERE id=?";

	private static final String queryUpdateCandidato = "UPDATE candidatos SET nombre_completo=?, partido=?, num_partido=?, color_partido=? WHERE id=?";
	
private Conexion conexion = Conexion.getInstance();


public Candidato findById(int id) throws Exception {
	 ResultSet rs = null;
	 PreparedStatement st = null;
	 try{
		st = conexion.dameConnection().prepareStatement(queryConsultarCandidato);
		st.setInt(1, id);
		rs = st.executeQuery();
		if (rs.next()) {
			return new Candidato(rs.getInt("id"),     
	                rs.getString("nombreCompleto"),   
	                rs.getString("partido"),
	                rs.getInt("num_partido"),
	                rs.getString("colorPartido"),     	                
	                rs.getInt("votos"));
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

public List<Candidato> list() throws Exception {

	PreparedStatement st= null;
	ResultSet rs = null;
	List<Candidato> rodados = new ArrayList<Candidato>();

	try {
		st = conexion.dameConnection().prepareStatement(queryList);
		rs = st.executeQuery();
		
		while (rs.next()) {
		    rodados.add(new Candidato(
		    	rs.getInt("id"),
		        rs.getString("patente"),
		        TipoEnum.valueOf(rs.getString("tipo")),
		        rs.getString("motor"),
		        rs.getString("color"),
		        CajaEnum.valueOf(rs.getString("tipo_caja")),
		        ConsumoEnum.valueOf(rs.getString("tipo_consumo"))
		    ));
		}
		
	} catch (Exception e) {
		System.out.println(e.getCause());
	}finally {
		st.close();
		rs.close();
	}
	
	return rodados;
}

public void save(String patente, TipoEnum tipo, String motor, String color, CajaEnum tipoCaja,
		ConsumoEnum tipoConsumo) throws ErrorException {

	 ResultSet rs = null;
	 PreparedStatement st = null;
	 try{
		st = conexion.dameConnection().prepareStatement(queryAddRodado);
		st.setString(1, patente);
        st.setString(2, tipo.name());
        st.setString(3, motor);
        st.setString(4, color);
        st.setString(5, tipoCaja.name());
        st.setString(6, tipoConsumo.name());
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

}
