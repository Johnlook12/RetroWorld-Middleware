package com.pinguela.retroworld.model;

import java.util.Date;

public class Modificacion extends AbstractValueObject{
	public static final int MODIFICACION_TITULO=4;
	public static final int MODIFICACION_PRECIO=2;
	public static final int MODIFICACION_DESCRIPCION=3;
	public static final int MODIFICACION_IMAGEN=1;
	
	private Long id;
	private Date fecha;
	private Long idAnuncio;
	private String tituloAnuncio;
	private Long idEmpleado;
	private String tipoModificacion;
	private Integer idTipoModificacion;
	
	public Modificacion() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(Long idAnuncio) {
		this.idAnuncio = idAnuncio;
	}

	public String getTituloAnuncio() {
		return tituloAnuncio;
	}

	public void setTituloAnuncio(String tituloAnuncio) {
		this.tituloAnuncio = tituloAnuncio;
	}

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getTipoModificacion() {
		return tipoModificacion;
	}

	public void setTipoModificacion(String tipoModificacion) {
		this.tipoModificacion = tipoModificacion;
	}

	public Integer getIdTipoModificacion() {
		return idTipoModificacion;
	}

	public void setIdTipoModificacion(Integer idTipoModificacion) {
		this.idTipoModificacion = idTipoModificacion;
	}
	
	
}
