package com.sample.core.service;

import java.util.List;

import com.sample.core.domain.Candidato;

public interface CandidatoService {
	
public List<Candidato> listarCandidatos() throws Exception;
	
	public Candidato consultarCandidato(int id) throws Exception;

	public void crearCandidato(int id, String nombreCompleto, String partido, int numPartido, String colorPartido, int votos) throws Exception;
	
	public void delete(int id) throws Exception;
}
