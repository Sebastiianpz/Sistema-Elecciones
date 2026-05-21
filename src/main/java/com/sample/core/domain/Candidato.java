package com.sample.core.domain;

public class Candidato extends Persona {

	String nombreCompleto;
	String partido;
	String colorPartido;
	int votos;
	
	public Candidato() {
		super();
	}

	public Candidato(String nombreCompleto, String partido, String colorPartido, int votos) {
		super();
		this.nombreCompleto = nombreCompleto;
		this.partido = partido;
		this.colorPartido = colorPartido;
		this.votos = votos;
	}


	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getPartido() {
		return partido;
	}

	public void setPartido(String partido) {
		this.partido = partido;
	}

	public String getColorPartido() {
		return colorPartido;
	}

	public void setColorPartido(String colorPartido) {
		this.colorPartido = colorPartido;
	}

	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}
	
	
}
