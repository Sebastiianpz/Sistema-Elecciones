package com.sample.core.domain;

import java.sql.Date;

public class Usuario extends GenericEntity{
	
	String usuario;
	String contrasena;
	String nombreCompleto;
	String partido;
	int numPartido;
	String colorPartido;
	int votos;
	String macAddress;
	String nombreMac;
	boolean estadoPc;
	int votosEmitidos;
	Date fechaRegistro;
	
	public Usuario() {
		super();
	}
	
	public Usuario(String usuario, String contrasena, String nombreCompleto, String partido, int numPartido,
			String colorPartido, int votos, String macAddress, String nombreMac, boolean estadoPc) {
	    super.setId(id); 
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombreCompleto = nombreCompleto;
		this.partido = partido;
		this.numPartido = numPartido;
		this.colorPartido = colorPartido;
		this.votos = votos;
		this.macAddress = macAddress;
		this.nombreMac = nombreMac;
		this.estadoPc = estadoPc;
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

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getNombreMac() {
		return nombreMac;
	}

	public void setNombreMac(String nombreMac) {
		this.nombreMac = nombreMac;
	}

	public boolean isEstadoPc() {
		return estadoPc;
	}

	public void setEstadoPc(boolean estadoPc) {
		this.estadoPc = estadoPc;
	}


	
}
