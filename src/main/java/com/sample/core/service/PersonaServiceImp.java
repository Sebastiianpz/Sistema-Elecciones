package com.sample.core.service;

import java.util.List;

import com.sample.core.dao.PersonaDaoImp;
import com.sample.core.domain.Persona;
import com.sample.core.dao.PersonaDao;


public class PersonaServiceImp implements PersonaService{

	private PersonaDao personaDao = new PersonaDaoImp();

	
	@Override
	public List<Persona> listarPersonas() throws Exception {
		// TODO Auto-generated method stub
		return personaDao.list();
	}

	@Override
	public Persona consultarPersona(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
