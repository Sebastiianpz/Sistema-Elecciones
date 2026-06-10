package ar.edu.padron.service;

import java.util.List;
import ar.edu.padron.dao.PersonaDAO;
import ar.edu.padron.dao.PersonaDaoImp;
import ar.edu.padron.domain.Persona;

public class PersonaServiceImp implements PersonaService {

    // El Service usa al DAO
    private PersonaDAO personaDao = new PersonaDaoImp();

 
    @Override
    public void registrarPersona(Persona p, byte[] foto, String nombreFoto) throws Exception {
        if (personaDao.existeDocumento(p.getNroDocumento())) {
            throw new Exception("No se puede registrar: El DNI " + p.getNroDocumento() + " ya existe.");
        }
        
        // 1. Guardar la persona en la base de datos
        personaDao.save(p);

        // 2. Si el usuario subio una foto, buscar el ID real asignado por la BD
        if (foto != null && foto.length > 0) {
            Persona personaRecienCreada = personaDao.findByDocumento(p.getNroDocumento());
            
            if (personaRecienCreada != null) {
                // Guardar la imagen vinculada al ID correcto
                personaDao.saveImagen(personaRecienCreada.getId(), foto, nombreFoto);
            }
        }
    }


    @Override
    public List<Persona> listarPadron() throws Exception {
        // RF-9: Simplemente pedimos la lista al DAO
        return personaDao.findAll();
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

    @Override
    public byte[] obtenerFotoDni(int personaId) throws Exception {
        // RF-10: Recuperar la imagen
        return personaDao.getImagen(personaId);
    }


	@Override
	public void eliminarPersona(String dni) throws Exception {
		// 1. Llama al DAO modificado (que ahora borra imagen + persona)
		boolean exito = personaDao.delete(dni);
		
		// 2. Controla si el DNI existía
		if (!exito) {
			throw new Exception("No se pudo eliminar: El ciudadano con DNI " + dni + " no existe en el sistema.");
		}
	}

	@Override
	public void modificarPersona(Persona p) throws Exception {
		// 1. Validamos que el ciudadano exista en el padrón
		if (!personaDao.existeDocumento(p.getNroDocumento())) {
			throw new Exception("No se puede modificar: El DNI " + p.getNroDocumento() + " no está registrado.");
		}
		
		// 2. Si existe, mandamos los nuevos datos al DAO
		boolean exito = personaDao.update(p);
		
		if (!exito) {
			throw new Exception("Error interno: No se pudieron impactar los cambios del ciudadano.");
		}
	}




}
