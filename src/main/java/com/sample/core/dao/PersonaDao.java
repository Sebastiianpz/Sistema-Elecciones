package com.sample.core.dao;

import com.sample.core.domain.Persona;


public interface PersonaDao {

public Persona findById(int id) throws Exception;

public Persona findByDocumento(String nroDocumento) throws Exception;
	
}
