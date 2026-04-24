package ar.edu.padron.domain;

import java.time.LocalDate;

public class Persona {
	private int id;
	private String nroDocumento;
	private String apellido;
	private String nombre;
	private LocalDate fechaNacimiento;
	private char sexo;
	private String domicilio;
	private boolean habilitadoVotar;

	public Persona() {
		super();
	}

	public Persona(int id, String nroDocumento, String apellido, String nombre, LocalDate fechaNacimiento, char sexo,
			String domicilio, boolean habilitadoVotar) {
		super();
		this.id = id;
		this.nroDocumento = nroDocumento;
		this.apellido = apellido;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
		this.domicilio = domicilio;
		this.habilitadoVotar = habilitadoVotar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public boolean isHabilitadoVotar() {
		return habilitadoVotar;
	}

	public void setHabilitadoVotar(boolean habilitadoVotar) {
		this.habilitadoVotar = habilitadoVotar;
	}

	
	
}
