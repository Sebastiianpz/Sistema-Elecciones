package com.sample.core.domain;
import java.sql.Date;
public class GestionDeMacs extends GenericEntity {
	String macAddress;
	String nombreMac;
	boolean estadoPc;
	int votosEmitidos;
	Date fechaRegistro;
	int usuarioId;
	
	public GestionDeMacs() {
		super();
	}
	public GestionDeMacs(int id, String macAddress, String nombreMac, boolean estadoPc, int votosEmitidos, Date fechaRegistro, int usuarioId) {
	    super.setId(id);
		this.macAddress = macAddress;
		this.nombreMac = nombreMac;
		this.estadoPc = estadoPc;
		this.votosEmitidos = votosEmitidos;
		this.fechaRegistro = fechaRegistro;
		this.usuarioId = usuarioId;
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
	public int getVotosEmitidos() {
		return votosEmitidos;
	}
	public void setVotosEmitidos(int votosEmitidos) {
		this.votosEmitidos = votosEmitidos;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public int getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	
}
