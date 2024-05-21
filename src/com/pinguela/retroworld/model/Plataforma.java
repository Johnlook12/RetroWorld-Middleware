package com.pinguela.retroworld.model;

import java.util.Date;

public class Plataforma extends AbstractValueObject{
	private int id;
	private String nombre;
	private Date fechaLanzamiento;
	
	public Plataforma() {
		
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

	public Date getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	public void setFechaLanzamiento(Date fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}
}
