package com.pinguela.retroworld.model;

public class EstadoPedido extends AbstractValueObject{
	public static final int PENDIENTE = 1;
	public static final int ENVIADO = 2;
	public static final int CANCELADO = 3;
	public static final int COMPLETADO = 4;
	public static final int EN_PROCESO = 5;
	
	private int id;
	private String nombre;
	
	public EstadoPedido() {
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
