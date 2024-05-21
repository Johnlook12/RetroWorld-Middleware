package com.pinguela.retroworld.model;

public class TipoModificacion extends AbstractValueObject{
	private int id;
	private String nombre;
	
	public TipoModificacion() {
		
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
}
