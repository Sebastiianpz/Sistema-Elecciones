package ar.edu.padron.service;

import java.util.List;

import ar.edu.padron.domain.Persona;

public interface PersonaService {

	public void registrarPersona(Persona p, byte[] foto, String nombreFoto) throws Exception;

	public List<Persona> listarPadron() throws Exception;

	public void conmutarHabilitacion(int id, boolean estadoActual) throws Exception;

	Persona buscarPorDocumento(String dni) throws Exception;

	byte[] obtenerFotoDni(int personaId) throws Exception;

	void eliminarPersona(String dni) throws Exception;
	
	void modificarPersona(Persona p) throws Exception;


}
