package com.sample.core.dao;

import java.util.List;

import com.sample.core.domain.Persona;


public interface PersonaDao {

public Persona findById(int id) throws Exception;
	
	public List<Persona> list() throws Exception;

}
