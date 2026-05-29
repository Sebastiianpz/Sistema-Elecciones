package com.sample.core.service;

import java.util.List;

import com.sample.core.dao.CandidatoDao;
import com.sample.core.dao.CandidatoDaoImp;
import com.sample.core.domain.Candidato;


public class CandidatoServiceImp implements CandidatoService{

	private CandidatoDao candidatoDao = new CandidatoDaoImp();

	
	@Override
	public List<Candidato> listarCandidatos() throws Exception {
		return candidatoDao.list();
	}


	@Override
	public Candidato consultarCandidato(int id) throws Exception {
		return candidatoDao.findById(id);
	}

	

	@Override
	public void delete(int id) throws Exception {
		candidatoDao.delete(id);
	}

	@Override
	public void crearCandidato(int id, String nombreCompleto, String partido, int numPartido, String colorPartido,
			int votos) throws Exception {
		candidatoDao.save(numPartido, colorPartido, false, false, nombreCompleto, partido, colorPartido, votos);
	}
}
