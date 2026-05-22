package com.sample.core.service;

import java.util.List;

import com.sample.core.domain.Persona;

public interface PersonaService {
	
public List<Persona> listarBebidas() throws Exception;
	
	public Persona consultarBebida(int id) throws Exception;

}
