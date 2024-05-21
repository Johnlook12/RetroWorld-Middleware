package com.pinguela.retroworld.service;

import java.util.Date;

import com.pinguela.retroworld.model.AbstractCriteria;

public class VideojuegoCriteria extends AbstractCriteria{
	public static final String ORDER_BY_FECHA = "V.FECHA_LANZAMIENTO";
	public static final String ORDER_BY_NOMBRE = "V.NOMBRE";
	public static final String ORDER_BY_ID = "V.ID";
	
	private Integer id;
	private String nombre;
	private Integer idGenero;
	private String nombreGenero;
	private Integer idIdioma;
	private String nombreIdioma;
	private Integer idDesarrolladora;
	private String nombreDesarrolladora;
	private Integer idPlataforma;
	private String nombrePlataforma;
	private Date fechaLanzamientoDesde;
	private Date fechaLanzamientoHasta;
	private String orderBy = ORDER_BY_ID;
	private Boolean ascDesc = Boolean.TRUE;
	
	public VideojuegoCriteria() {
		
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

	public Integer getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(Integer idGenero) {
		this.idGenero = idGenero;
	}

	public String getNombreGenero() {
		return nombreGenero;
	}

	public void setNombreGenero(String nombreGenero) {
		this.nombreGenero = nombreGenero;
	}

	public Integer getIdDesarrolladora() {
		return idDesarrolladora;
	}

	public void setIdDesarrolladora(Integer idDesarrolladora) {
		this.idDesarrolladora = idDesarrolladora;
	}

	public String getNombreDesarrolladora() {
		return nombreDesarrolladora;
	}

	public void setNombreDesarrolladora(String nombreDesarrolladora) {
		this.nombreDesarrolladora = nombreDesarrolladora;
	}

	public Integer getIdPlataforma() {
		return idPlataforma;
	}

	public void setIdPlataforma(Integer idPlataforma) {
		this.idPlataforma = idPlataforma;
	}

	public String getNombrePlataforma() {
		return nombrePlataforma;
	}

	public void setNombrePlataforma(String nombrePlataforma) {
		this.nombrePlataforma = nombrePlataforma;
	}

	public Date getFechaLanzamientoDesde() {
		return fechaLanzamientoDesde;
	}

	public void setFechaLanzamientoDesde(Date fechaLanzamientoDesde) {
		this.fechaLanzamientoDesde = fechaLanzamientoDesde;
	}

	public Date getFechaLanzamientoHasta() {
		return fechaLanzamientoHasta;
	}

	public void setFechaLanzamientoHasta(Date fechaLanzamientoHasta) {
		this.fechaLanzamientoHasta = fechaLanzamientoHasta;
	}

	public Integer getIdIdioma() {
		return idIdioma;
	}

	public void setIdIdioma(Integer idIdioma) {
		this.idIdioma = idIdioma;
	}

	public String getNombreIdioma() {
		return nombreIdioma;
	}

	public void setNombreIdioma(String nombreIdioma) {
		this.nombreIdioma = nombreIdioma;
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
	
}
