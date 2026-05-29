package com.sample.core.service;

import java.util.List;

import com.sample.core.dao.VotosDao;
import com.sample.core.dao.VotosDaoImp;
import com.sample.core.domain.Voto;

public class VotoServiceImp implements VotoService {

private VotosDao votoDao = new VotosDaoImp();

	
	@Override
	public List<Voto> listarVotos() throws Exception {
		return votoDao.list();
	}
}
