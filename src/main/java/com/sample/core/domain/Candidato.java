package com.sample.core.domain;

public class Candidato extends GenericEntity {

	String nombreCompleto;
	String partido;
	int numPartido;
	String colorPartido;
	int votos;
	
	public Candidato() {
		super();
	}
	
	public Candidato(int id, String nombreCompleto, String partido, int numPartido, String colorPartido, int votos) {
	    super.setId(id); 
	    this.nombreCompleto = nombreCompleto;
	    this.partido = partido;
	    this.numPartido = numPartido;
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
	
	public int getNumPartido() {
		return numPartido;
	}

	public void setNumPartido(int numPartido) {
		this.numPartido = numPartido;
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
