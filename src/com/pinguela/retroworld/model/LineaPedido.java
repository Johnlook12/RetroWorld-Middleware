package com.pinguela.retroworld.model;

public class LineaPedido extends AbstractValueObject{
	private Long id;
	private Double precio;
	private Long idPedido;
	private Long idVideojuego;
	private String nombreVideojuego;
	
	public LineaPedido() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Long getIdVideojuego() {
		return idVideojuego;
	}

	public void setIdVideojuego(Long idVideojuego) {
		this.idVideojuego = idVideojuego;
	}

	public String getNombreVideojuego() {
		return nombreVideojuego;
	}

	public void setNombreVideojuego(String nombreVideojuego) {
		this.nombreVideojuego = nombreVideojuego;
	}

	@Override
	public String toString() {
		return "LineaPedido [id=" + id + ", precio=" + precio + ", idPedido=" + idPedido + ", idVideojuego="
				+ idVideojuego + ", nombreVideojuego=" + nombreVideojuego + "]";
	}
	
}
