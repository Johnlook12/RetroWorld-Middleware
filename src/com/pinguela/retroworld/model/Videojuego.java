package com.pinguela.retroworld.model;

import java.util.Date;
import java.util.List;

public class Videojuego extends AbstractValueObject{
	private Long id;
	private String nombre;
	private String descripcion;
	private Date fechaLanzamiento;
	private Integer idDesarrolladora;
	private String nombreDesarrolladora;
	private String nombreIdioma;
	private String nombrePlataforma;
	private String nombreGenero;
	private List<Idioma> idiomas;
	private List<Plataforma> plataformas;
	private List<Genero> generos;
	
	
	public Videojuego() {
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Date getFechaLanzamiento() {
		return fechaLanzamiento;
	}


	public void setFechaLanzamiento(Date fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
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


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getNombreIdioma() {
		return nombreIdioma;
	}


	public void setNombreIdioma(String nombreIdioma) {
		this.nombreIdioma = nombreIdioma;
	}


	public String getNombrePlataforma() {
		return nombrePlataforma;
	}


	public void setNombrePlataforma(String nombrePlataforma) {
		this.nombrePlataforma = nombrePlataforma;
	}


	public String getNombreGenero() {
		return nombreGenero;
	}


	public void setNombreGenero(String nombreGenero) {
		this.nombreGenero = nombreGenero;
	}


	public List<Idioma> getIdiomas() {
		return idiomas;
	}


	public void setIdiomas(List<Idioma> idiomas) {
		this.idiomas = idiomas;
	}


	public List<Plataforma> getPlataformas() {
		return plataformas;
	}


	public void setPlataformas(List<Plataforma> plataformas) {
		this.plataformas = plataformas;
	}


	public List<Genero> getGeneros() {
		return generos;
	}


	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}
}
