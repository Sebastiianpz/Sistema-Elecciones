package com.sample.core.dao;

import java.util.List;

import com.sample.core.domain.Usuario;

public interface UsuarioDao {
	
	public Usuario findById(int id) throws Exception;
	
	
}