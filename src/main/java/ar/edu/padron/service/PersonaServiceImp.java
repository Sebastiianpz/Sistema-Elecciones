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
        
<<<<<<< Updated upstream
        // 1. Guardamos la persona (devuelve 1 si tuvo Èxito)
        personaDao.save(p);

        // 2. Si el usuario subiÛ una foto, buscamos el ID real asignado por la BD
        if (foto != null && foto.length > 0) {
            // Buscamos la persona reciÈn insertada usando su DNI
            Persona personaRecienCreada = personaDao.findByDocumento(p.getNroDocumento());
            
            if (personaRecienCreada != null) {
                // Ahora sÌ, guardamos la imagen con el ID autoincremental correcto (ej: 42, 43, etc.)
=======
        // 1. Guardamos la persona (devuelve 1 si tuvo √©xito)
        personaDao.save(p);

        // 2. Si el usuario subi√≥ una foto, buscamos el ID real asignado por la BD
        if (foto != null && foto.length > 0) {
            // Buscamos la persona reci√©n insertada usando su DNI
            Persona personaRecienCreada = personaDao.findByDocumento(p.getNroDocumento());
            
            if (personaRecienCreada != null) {
                // Ahora s√≠, guardamos la imagen con el ID autoincremental correcto (ej: 42, 43, etc.)
>>>>>>> Stashed changes
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
        boolean nuevoEstado = !estadoActual;
        boolean exito = personaDao.updateHabilitado(id, nuevoEstado);
        
        if (!exito) {
            throw new Exception("Error al intentar cambiar el estado de la persona.");
        }
    }

    @Override
    public Persona buscarPorDocumento(String dni) throws Exception {
        // RF-6: Busqueda para el buscador AJAX
        Persona p = personaDao.findByDocumento(dni);
        if (p == null) {
            throw new Exception("No se encontr√≥ ninguna persona con el DNI: " + dni);
        }
        return p;
    }

    @Override
    public byte[] obtenerFotoDni(int personaId) throws Exception {
        // RF-10: Recuperar la imagen
        return personaDao.getImagen(personaId);
    }
}
