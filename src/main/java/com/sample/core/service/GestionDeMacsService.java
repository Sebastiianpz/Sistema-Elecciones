package com.sample.core.service;

import java.sql.Date;
import java.util.List;

import com.sample.core.domain.GestionDeMacs;

public interface GestionDeMacsService {

public List<GestionDeMacs> listarGestionDeMacs() throws Exception;
	
	public GestionDeMacs consultarGestionDeMacs(int id) ;
	
	public void crearGestionDeMacs(int id, String macAddress, String nombreMac, boolean estadoPc, int votosEmitidos, Date fechaRegistro, int usuarioId);
	
	public void delete(int id)throws Exception;
}
