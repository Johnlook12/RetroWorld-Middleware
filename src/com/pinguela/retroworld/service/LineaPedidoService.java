package com.pinguela.retroworld.service;


import com.pinguela.DataException;
import com.pinguela.retroworld.model.LineaPedido;

public interface LineaPedidoService {
	public LineaPedido findById(Long id) throws DataException;
}
