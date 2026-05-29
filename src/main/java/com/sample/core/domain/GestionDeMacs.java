package com.sample.core.domain;
<<<<<<< HEAD
import java.sql.Date;
=======

import java.sql.Date;

>>>>>>> aefff63dbed6ca29722abf9cf23697141105d11e
public class GestionDeMacs extends GenericEntity {
	String macAddress;
	String nombreMac;
	boolean estadoPc;
	int votosEmitidos;
<<<<<<< HEAD
	Date fechaRegistro;
=======
	Date fechaRegistro; 
>>>>>>> aefff63dbed6ca29722abf9cf23697141105d11e
	int usuarioId;
	
	public GestionDeMacs() {
		super();
	}
<<<<<<< HEAD
	public GestionDeMacs(int id, String macAddress, String nombreMac, boolean estadoPc, int votosEmitidos, Date fechaRegistro, int usuarioId) {
	    super.setId(id);
=======

	public GestionDeMacs(int id, String macAddress, String nombreMac, boolean estadoPc, int votosEmitidos, Date fechaRegistro, int usuarioId) {
	    super.setId(id); 
>>>>>>> aefff63dbed6ca29722abf9cf23697141105d11e
		this.macAddress = macAddress;
		this.nombreMac = nombreMac;
		this.estadoPc = estadoPc;
		this.votosEmitidos = votosEmitidos;
		this.fechaRegistro = fechaRegistro;
		this.usuarioId = usuarioId;
<<<<<<< HEAD
=======

>>>>>>> aefff63dbed6ca29722abf9cf23697141105d11e
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
<<<<<<< HEAD
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
=======

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

>>>>>>> aefff63dbed6ca29722abf9cf23697141105d11e
	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	
}
