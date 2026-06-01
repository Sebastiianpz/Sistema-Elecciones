package ar.edu.padron.service;

import ar.edu.padron.domain.Usuario;
import ar.edu.padron.dao.UsuarioDAO;
import ar.edu.padron.dao.UsuarioDaoImp;

public class UsuarioServiceImp implements UsuarioService {

	private UsuarioDAO usuarioDao = new UsuarioDaoImp();

	@Override
	public Usuario buscarUsuarioPorNombre(String username) throws Exception {

		return usuarioDao.findByUsername(username);
	}

	@Override
	public boolean login(String username, String password) throws Exception {

		return usuarioDao.validarCredenciales(username, password);
	}

}
