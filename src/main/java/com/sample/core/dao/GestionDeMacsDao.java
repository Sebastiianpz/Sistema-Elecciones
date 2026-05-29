package com.sample.core.dao;

import java.sql.Date;
import java.util.List;

import com.sample.core.domain.Candidato;
import com.sample.core.domain.GestionDeMacs;


public interface GestionDeMacsDao {

    public GestionDeMacs findById(int id) throws Exception;
	
	public List<GestionDeMacs> list() throws Exception;

    public void save(String macAddress, String nombreMac, boolean estadoPc, int votosEmitidos, Date fechaRegistro, int usuarioId) throws Exception;
	
	public void delete(int id) throws Exception;
	
	public void cambiarEstado(GestionDeMacs m) throws Exception;
}
