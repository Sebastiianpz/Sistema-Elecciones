package com.sample.core.dao;

import java.util.List;

import com.sample.core.domain.Voto;

public class VotosDaoImp implements VotosDao {
	
	private static final String queryConsultarVotos = "SELECT id, nro_documento, habilitado_votar, ya_voto FROM personas WHERE id=?";
	
	private static final String queryList = "SELECT id, nro_documento, habilitado_votar, ya_voto FROM personas";


	@Override
	public Voto findById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Voto> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
