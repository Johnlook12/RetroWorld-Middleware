package com.pinguela.retroworld.service;

import java.util.Date;

import com.pinguela.retroworld.model.AbstractCriteria;

public class AnuncioCriteria extends AbstractCriteria{
	public static final String ORDER_BY_PRECIO = "A.PRECIO";
	public static final String ORDER_BY_TITULO = "A.TITULO";
	public static final String ORDER_BY_FECHA_INICIO = "A.FECHA_INICIO";
	public static final String ORDER_BY_FECHA_FIN = "A.FECHA_FIN";
	public static final String ORDER_BY_ESTADO_ANUNCIO = "A.ESTADO_ANUNCIO_ID";
	public static final String ORDER_BY_ESTADO_VIDEOJUEGO = "A.ESTADO_VIDEOJUEGO_ID";
	
	
	private String nombre;
	private String descripcion;
	private Long idAnuncio;
	private Long idUsuario;
	private Long idEmpleado;
	private Date fechaInicio;
	private Date fechaFin;
	private Double precioDesde;
	private Double precioHasta;
	private Short idEstado; //
	private Long idVideojuego; //
	private String nombreVideojuego;
	private Integer idEstadoAnuncio;
	private Integer idGeneroVideojuego;
	private Integer idIdiomaVideojuego; 
	private Integer idPlataformaVideojuego; 
	private String orderBy = ORDER_BY_PRECIO;
	private Boolean ascDesc= Boolean.FALSE;
	
	public AnuncioCriteria() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(Long idAnuncio) {
		this.idAnuncio = idAnuncio;
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

	public Double getPrecioDesde() {
		return precioDesde;
	}

	public void setPrecioDesde(Double precioDesde) {
		this.precioDesde = precioDesde;
	}

	public Double getPrecioHasta() {
		return precioHasta;
	}

	public void setPrecioHasta(Double precioHasta) {
		this.precioHasta = precioHasta;
	}

	public Short getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Short idEstado) {
		this.idEstado = idEstado;
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

	public Integer getIdEstadoAnuncio() {
		return idEstadoAnuncio;
	}

	public void setIdEstadoAnuncio(Integer idEstadoAnuncio) {
		this.idEstadoAnuncio = idEstadoAnuncio;
	}


	public Integer getIdGeneroVideojuego() {
		return idGeneroVideojuego;
	}

	public void setIdGeneroVideojuego(Integer idGeneroVideojuego) {
		this.idGeneroVideojuego = idGeneroVideojuego;
	}

	public Integer getIdIdiomaVideojuego() {
		return idIdiomaVideojuego;
	}

	public void setIdIdiomaVideojuego(Integer idIdiomaVideojuego) {
		this.idIdiomaVideojuego = idIdiomaVideojuego;
	}

	public Integer getIdPlataformaVideojuego() {
		return idPlataformaVideojuego;
	}

	public void setIdPlataformaVideojuego(Integer idPlataformaVideojuego) {
		this.idPlataformaVideojuego = idPlataformaVideojuego;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Boolean getAscDesc() {
		return ascDesc;
	}

	public void setAscDesc(Boolean ascDesc) {
		this.ascDesc = ascDesc;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
