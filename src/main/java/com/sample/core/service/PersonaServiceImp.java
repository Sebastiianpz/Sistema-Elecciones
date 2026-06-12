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

	@Override
    public void conmutarHabilitacion(int id, boolean estadoActual) throws Exception {
        // RF-7 y 8: Invertimos el estado (Toggle)
    	 boolean exito = personaDao.updateHabilitado(id, estadoActual);
    	    if (!exito) {
    	        throw new Exception("Error al intentar cambiar el estado de la persona.");
    	    }
    }

    @Override
    public Persona buscarPorDocumento(String dni) throws Exception {
        // RF-6: Busqueda para el buscador AJAX
        Persona p = personaDao.findByDocumento(dni);
        if (p == null) {
            throw new Exception("No se encontró ninguna persona con el DNI: " + dni);
        }
        return p;
    }

}
