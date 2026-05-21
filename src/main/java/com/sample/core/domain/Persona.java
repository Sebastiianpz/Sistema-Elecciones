package com.sample.core.domain;

public abstract class Persona {

	String nroDocumento;
	boolean habilitadoVotar;
	boolean yaVoto;
	
	public Persona() {
		super();
	}

	public Persona(String nroDocumento, boolean habilitadoVotar, boolean yaVoto) {
		super();
		this.nroDocumento = nroDocumento;
		this.habilitadoVotar = habilitadoVotar;
		this.yaVoto = yaVoto;
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
	
	
}
