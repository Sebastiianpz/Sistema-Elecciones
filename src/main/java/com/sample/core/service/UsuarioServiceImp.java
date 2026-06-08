package com.sample.core.service;

import com.sample.core.dao.UsuarioDAO;
import com.sample.core.dao.UsuarioDaoImp;
import com.sample.core.domain.Usuario;

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
