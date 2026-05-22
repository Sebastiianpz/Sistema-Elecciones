package com.sample.core.domain;

public class UsuarioAdmin extends Persona{
	
	String usuario;
	String contrasena;
	
	public UsuarioAdmin() {
		super();
	}

	public UsuarioAdmin(int id, String nroDocumento, boolean habilitadoVotar, boolean yaVoto, String usuario, String contrasena) {
		super.setId(id);	
		super.setNroDocumento(nroDocumento);		
		super.setHabilitadoVotar(habilitadoVotar);		
		super.setYaVoto(yaVoto);		
		this.usuario = usuario;
		this.contrasena = contrasena;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	
}
