package com.sample.core.dao;

import java.util.List;

import com.sample.core.domain.GestionDeMacs;


public interface GestionDeMacsDao {

    public GestionDeMacs findById(int id) throws Exception;
	
	public List<GestionDeMacs> list() throws Exception;

    public void save(String macAddress, String nombre) throws Exception;
	
	public void delete(int id) throws Exception;
	
	public void cambiarEstado(int id, int habilitada) throws Exception;
}
