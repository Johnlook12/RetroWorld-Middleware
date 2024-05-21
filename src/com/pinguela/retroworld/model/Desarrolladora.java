package com.pinguela.retroworld.model;

import java.util.Date;

public class Desarrolladora	extends AbstractValueObject{
	private int id;
	private String nombre;
	private Date fechaFundación;
	private Short idPais;
	
	public Desarrolladora() {
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaFundación() {
		return fechaFundación;
	}

	public void setFechaFundación(Date fechaFundación) {
		this.fechaFundación = fechaFundación;
	}
	public Short getIdPais() {
		return idPais;
	}
	public void setIdPais(Short idPais) {
		this.idPais = idPais;
	}
	
}
