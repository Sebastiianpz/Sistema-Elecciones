package com.sample.core.domain;

import java.sql.Date;

public class Voto extends GenericEntity{

	int personaId;
	int candidatoId;
	int pcId;
	Date fechaVoto;
	
	public Voto() {
		super();
	}

	public Voto(int id, int personaId, int candidatoId, int pcId, Date fechaVoto) {
		super.setId(id);
		this.personaId = personaId;
		this.candidatoId = candidatoId;
		this.pcId = pcId;
		this.fechaVoto = fechaVoto;
	}

	public int getPersonaId() {
		return personaId;
	}

	public void setPersonaId(int personaId) {
		this.personaId = personaId;
	}

	public int getCandidatoId() {
		return candidatoId;
	}

	public void setCandidatoId(int candidatoId) {
		this.candidatoId = candidatoId;
	}

	public int getPcId() {
		return pcId;
	}

	public void setPcId(int pcId) {
		this.pcId = pcId;
	}

	public Date getFechaVoto() {
		return fechaVoto;
	}

	public void setFechaVoto(Date fechaVoto) {
		this.fechaVoto = fechaVoto;
	}

	
}
