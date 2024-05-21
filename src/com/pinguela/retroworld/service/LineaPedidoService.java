package com.pinguela.retroworld.service;


import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.LineaPedido;

public interface LineaPedidoService {
	public LineaPedido findById(Long id) throws DataException;
}
