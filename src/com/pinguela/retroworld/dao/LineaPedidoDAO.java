package com.pinguela.retroworld.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.LineaPedido;

public interface LineaPedidoDAO {
	public LineaPedido findById(Connection conn, Long id) throws DataException;
	public List<LineaPedido> findByIdPedido(Connection conn, Long idPedido) throws DataException;
	public void create (Connection conn, Long idPedido, List<LineaPedido> lineas) throws DataException;
	public boolean deleteByPedido(Connection conn, Long idPedido) throws DataException;
}
