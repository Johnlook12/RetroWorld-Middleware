package com.pinguela.retroworld.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.EstadoPedido;

public interface EstadoPedidoDAO {
	public List<EstadoPedido> findByAll(Connection conn) throws DataException;
}
