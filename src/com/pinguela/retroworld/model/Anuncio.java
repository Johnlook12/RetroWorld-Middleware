package com.pinguela.retroworld.model;

import java.util.Date;

public class Anuncio extends AbstractValueObject {
	public static final int ESTADO_ACTIVO=1;
	public static final int ESTADO_FINALIZADO=2;
	public static final int ESTADO_PENDIENTE=3;

	
	private Long id;
	private String titulo;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private Double precio;
	private Long idVideojuego;
	private Long idUsuario;
	private Long idEmpleado;
	private Integer estadoVideojuego;
	private String nombreVideojuego;
	private Integer idEstadoAnuncio;
	private String estadoAnuncio;
	
	public Anuncio() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	
	public Long getIdVideojuego() {
		return idVideojuego;
	}

	public void setIdVideojuego(Long videojuegoId) {
		this.idVideojuego = videojuegoId;
	}
	
	

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Integer getEstadoVideojuego() {
		return estadoVideojuego;
	}

	public void setEstadoVideojuego(Integer estadoVideojuego) {
		this.estadoVideojuego = estadoVideojuego;
	}

	public String getNombreVideojuego() {
		return nombreVideojuego;
	}

	public void setNombreVideojuego(String nombreVideojuego) {
		this.nombreVideojuego = nombreVideojuego;
	}

	public String getEstadoAnuncio() {
		return estadoAnuncio;
	}

	public void setEstadoAnuncio(String estadoAnuncio) {
		this.estadoAnuncio = estadoAnuncio;
	}

	public Integer getIdEstadoAnuncio() {
		return idEstadoAnuncio;
	}

	public void setIdEstadoAnuncio(Integer idEstadoAnuncio) {
		this.idEstadoAnuncio = idEstadoAnuncio;
	}
}
