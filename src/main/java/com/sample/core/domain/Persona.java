package com.sample.core.domain;

public class Persona extends GenericEntity{

	String nroDocumento;
	boolean habilitadoVotar;
	boolean yaVoto;
	String rol;
	
	public Persona() {
		super();
	}

	public Persona(int id, String nroDocumento, boolean habilitadoVotar, boolean yaVoto, String rol) {
		super.setId(id);		
		this.nroDocumento = nroDocumento;
		this.habilitadoVotar = habilitadoVotar;
		this.yaVoto = yaVoto;
		this.rol = rol;

	}

	
	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public boolean isHabilitadoVotar() {
		return habilitadoVotar;
	}

	public void setHabilitadoVotar(boolean habilitadoVotar) {
		this.habilitadoVotar = habilitadoVotar;
	}

	public boolean isYaVoto() {
		return yaVoto;
	}

	public void setYaVoto(boolean yaVoto) {
		this.yaVoto = yaVoto;
	}
	
	public String getRol() {
		return rol;
	}
	
	public void setRol(String rol) {
		this.rol = rol;
	}
}
