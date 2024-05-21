package com.pinguela.retroworld.model;

import java.util.Date;

public class Empleado extends AbstractValueObject{
	public static final short TIPO_ADMINISTRADOR = 1;
	public static final short TIPO_EMPLEADO = 2;
	
	private Long id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String documentoIdentidad;
	private String telefono;
	private String email;
	private String password;
	private Direccion direccion;
	private Date fechaBaja;
	private Short idTipoEmpleado;
	
	public Empleado() {
		
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

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}

	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Direccion getDireccion() {
		return direccion;
	}



	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}



	public Date getFechaBaja() {
		return fechaBaja;
	}



	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}



	public Short getIdTipoEmpleado() {
		return idTipoEmpleado;
	}



	public void setIdTipoEmpleado(Short idTipoEmpleado) {
		this.idTipoEmpleado = idTipoEmpleado;
	}
	
}
