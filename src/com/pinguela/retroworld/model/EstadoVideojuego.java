package com.pinguela.retroworld.model;

public class EstadoVideojuego extends AbstractValueObject{
	
	private int id;
	private String nombre;
	private String descripcion;
	
	public EstadoVideojuego() {
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
