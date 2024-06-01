package com.pinguela.retroworld.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido extends AbstractValueObject{
	private Long id;
	private Date fecha;
	private Long idUsuario;
	private String nickNameUsuario;
	private String nombreUsuario;
	private Double precioTotal;
	private Integer idEstado;
	private String estado;
	private List<LineaPedido> lineas;
	
	public Pedido() {
		lineas = new ArrayList<LineaPedido>();
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

	public String getNickNameUsuario() {
		return nickNameUsuario;
	}

	public void setNickNameUsuario(String nickNameUsuario) {
		this.nickNameUsuario = nickNameUsuario;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public Double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<LineaPedido> getLineas() {
		return lineas;
	}

	public void setLineas(List<LineaPedido> lineas) {
		this.lineas = lineas;
	}
}
