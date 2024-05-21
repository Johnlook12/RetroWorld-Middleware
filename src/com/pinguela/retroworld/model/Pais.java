package com.pinguela.retroworld.model;

public class Pais extends AbstractValueObject{
	private Short id;
	private String nombre;
	private String iso2;
	private String iso3;
	private String telefonoCode;
	
	public Pais() {
		
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

	public String getIso2() {
		return iso2;
	}

	public void setIso2(String iso2) {
		this.iso2 = iso2;
	}

	public String getIso3() {
		return iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}

	public String getTelefonoCode() {
		return telefonoCode;
	}

	public void setTelefonoCode(String telefonoCode) {
		this.telefonoCode = telefonoCode;
	}

}
