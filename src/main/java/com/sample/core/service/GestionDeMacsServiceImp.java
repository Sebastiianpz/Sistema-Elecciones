package com.sample.core.service;

import java.sql.Date;
import java.util.List;

import com.sample.core.dao.GestionDeMacsDaoImp;
import com.sample.core.dao.GestionDeMacsDao;
import com.sample.core.domain.GestionDeMacs;

public class GestionDeMacsServiceImp implements GestionDeMacsService{

	private GestionDeMacsDao gestiondemacsDao = new GestionDeMacsDaoImp();
	@Override
	public List<GestionDeMacs> listarGestionDeMacs() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GestionDeMacs consultarGestionDeMacs(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void crearGestionDeMacs(int id, String macAddress, String nombreMac, boolean estadoPc, int votosEmitidos,
			Date fechaRegistro, int usuarioId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
