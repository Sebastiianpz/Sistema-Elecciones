package com.sample.core.service;

import java.util.List;

import com.sample.core.dao.PersonaDaoImp;
import com.sample.core.domain.Persona;
import com.sample.core.dao.PersonaDao;


public class PersonaServiceImp implements PersonaService{

	private PersonaDao personaDao = new PersonaDaoImp();


    @Override
    public Persona buscarPorDocumento(String dni) throws Exception {
        // RF-6: Busqueda para el buscador AJAX
        Persona p = personaDao.findBynroDocumento(dni);
        if (p == null) {
            throw new Exception("No se encontró ninguna persona con el DNI: " + dni);
        }
        return p;
    }

}
