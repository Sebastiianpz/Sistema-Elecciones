package ar.edu.padron.dao;

import java.util.List;

import ar.edu.padron.domain.Persona;

public class PersonaDaoImp implements PersonaDAO {

	@Override
	public int save(Persona p) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Persona findByDocumento(String dni) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Persona> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existeDocumento(String dni) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateHabilitado(int id, boolean habilitado) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveImagen(int personaId, byte[] img, String nombre) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public byte[] getImagen(int personaId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
