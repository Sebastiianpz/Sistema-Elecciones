package com.sample.core.domain;

public class Candidato extends Persona {

	String nombreCompleto;
	String partido;
	int numPartido;
	String colorPartido;
	int votos;
	
	public Candidato() {
		super();
	}

	public Candidato(int id, String nroDocumento, boolean habilitadoVotar, boolean yaVoto, String nombreCompleto, String partido, int numPartido, String colorPartido, int votos) {
		super.setId(id);	
		super.setNroDocumento(nroDocumento);		
		super.setHabilitadoVotar(habilitadoVotar);		
		super.setYaVoto(yaVoto);		
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
	
	public int getnumPartido() {
		return numPartido;
	}

	public void setnumPartido(int numPartido) {
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
