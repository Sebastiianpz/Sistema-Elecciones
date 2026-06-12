package com.sample.core.service;


import com.sample.core.domain.Persona;

public interface PersonaService {
	
	Persona buscarPorDocumento(String nroDocumento)throws Exception;

}
