package com.pinguela.retroworld.model;

public class Localidad extends AbstractValueObject{
	private Long codigoPostal;
	private String nombre;
	private Short idProvincia;
	private String nombreProvincia;
	
	public Localidad() {
	}

	public Long getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Long id) {
		this.codigoPostal = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Short getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Short idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getNombreProvincia() {
		return nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}
}
