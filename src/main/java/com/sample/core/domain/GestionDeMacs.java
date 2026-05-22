package com.sample.core.domain;

public class GestionDeMacs {

	String macAddress;
	String nombreMac;
	boolean estadoPc;
	
	public GestionDeMacs() {
		super();
	}

	public GestionDeMacs(String macAddress, String nombreMac, boolean estadoPc) {
		super();
		this.macAddress = macAddress;
		this.nombreMac = nombreMac;
		this.estadoPc = estadoPc;
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
