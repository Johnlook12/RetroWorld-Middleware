package com.pinguela.retroworld.model;

public class Idioma extends AbstractValueObject{
	public Integer id;
	public String nombre;
	
	public Idioma() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
