package com.pinguela.retroworld.model;

public class EstadoAnuncio extends AbstractValueObject{
	public static final int ACTIVO = 1;
	public static final int PENDIENTE = 3;
	public static final int FINALIZADO = 2;
	
	private int id;
	private Anuncio anuncio;
	
	public EstadoAnuncio() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}
}
