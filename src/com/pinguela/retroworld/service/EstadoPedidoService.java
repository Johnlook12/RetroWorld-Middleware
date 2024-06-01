package com.pinguela.retroworld.service;

import java.util.List;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.EstadoPedido;

public interface EstadoPedidoService {
	public List<EstadoPedido> findByAll() throws DataException;
}
