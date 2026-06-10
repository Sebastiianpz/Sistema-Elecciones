package com.sample.core.service;

import java.util.List;
import com.sample.core.domain.Usuario;

//Dentro de UsuarioService.java
public interface UsuarioService {


	// Firma para buscar los datos del usuario
	public Usuario buscarUsuarioPorNombre(String username) throws Exception;

	// Firma para el proceso de inicio de sesión
	public boolean login(String username, String password) throws Exception;
}
