package com.sample.core.dao;

import java.util.List;

import com.sample.core.domain.Persona;

import java.util.List;
import com.sample.core.domain.Persona;

public interface PersonaDAO {

    // INSERT INTO personas ... (Devuelve el ID generado)
    public int save(Persona p) throws Exception;

    // SELECT * FROM personas WHERE nro_documento = ?
    public Persona findByDocumento(String dni) throws Exception;

    // SELECT * FROM personas ORDER BY apellido, nombre
    public List<Persona> findAll() throws Exception;

    // SELECT COUNT(*) FROM personas WHERE nro_documento = ?
    public boolean existeDocumento(String dni) throws Exception;

    // UPDATE personas SET habilitado_votar = ? WHERE id = ?
    public boolean updateHabilitado(int id, boolean habilitado) throws Exception;

    // INSERT INTO imagenes_dni (persona_id, contenido, nombre_archivo)
    public boolean saveImagen(int personaId, byte[] img, String nombre) throws Exception;

    // SELECT contenido FROM imagenes_dni WHERE persona_id = ?
    public byte[] getImagen(int personaId) throws Exception;
}
