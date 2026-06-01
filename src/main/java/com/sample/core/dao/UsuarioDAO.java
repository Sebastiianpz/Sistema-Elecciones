package ar.edu.padron.dao;

import ar.edu.padron.domain.Usuario;

public interface UsuarioDAO {

    // SELECT id, username, password FROM usuarios WHERE username = ?
    public Usuario findByUsername(String user) throws Exception;

    // Busca el usuario, hashea y compara. Devuelve true si coinciden.
    public boolean validarCredenciales(String user, String pass) throws Exception;
    
}
