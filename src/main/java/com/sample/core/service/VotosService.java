package com.sample.core.service;

import java.util.List;

import com.sample.core.domain.Voto;

public interface VotosService {
    public List<Voto> list() throws Exception;
	
	public void save(int personaId, int candidatoId, int pcId) throws Exception;

	
}
