package com.sample.core.dao;

import java.util.List;

import com.sample.core.domain.Candidato;

public interface CandidatoDao {

	
	public Candidato findById(int id) throws Exception;
		
		public List<Candidato> list() throws Exception;

		public void save(int id, String nroDocumento, boolean habilitadoVotar, boolean yaVoto, String nombreCompleto, String partido, String colorPartido, int votos) throws Exception;

		public void delete(int id) throws Exception;

		public void update(Candidato c) throws Exception;
	}


