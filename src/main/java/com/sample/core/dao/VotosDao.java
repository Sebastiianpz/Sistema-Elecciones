package com.sample.core.dao;

import java.util.List;

import com.sample.core.domain.Voto;

public interface VotosDao {
	
	public List<Voto> list() throws Exception;
	
	public void save(int personaId, int candidatoId, int pcId) throws Exception;

}