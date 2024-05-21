package com.pinguela.retroworld.model;

public class Provincia extends AbstractValueObject{
	private Short id;
	private String nombre;
	private Short idPais;
	private String nombrePais;
	
	public Provincia() {
	}

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Short getIdPais() {
		return idPais;
	}

	public void setIdPais(Short idPais) {
		this.idPais = idPais;
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
}
