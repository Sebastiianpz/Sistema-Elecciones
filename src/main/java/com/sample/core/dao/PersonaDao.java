package com.sample.core.dao;

import java.util.List;

import com.sample.core.domain.Persona;



public interface PersonaDao {

public Persona findById(int id) throws Exception;
	
	public List<Persona> list() throws Exception;

	public void save(String nroDocumento, boolean habilitadoVotar,boolean yaVoto) throws Exception;

	public void delete(int id) throws Exception;

	public void update(Persona p) throws Exception;
}
