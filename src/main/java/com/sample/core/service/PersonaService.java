package com.sample.core.service;

import java.util.List;

import com.sample.core.domain.Persona;

public interface PersonaService {
	
public List<Persona> listarPersonas() throws Exception;
	
	public Persona consultarPersona(int id) throws Exception;

}
