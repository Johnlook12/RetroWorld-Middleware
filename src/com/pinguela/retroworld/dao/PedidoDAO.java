package com.pinguela.retroworld.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Pedido;

public interface PedidoDAO {
	public List<Pedido> findByAll(Connection conn)throws DataException;
	public Pedido findById(Connection conn, Long id) throws DataException;
	public List<Pedido> findByUsuario(Connection connn, Long idUsuario) throws DataException;
	public void create(Connection conn, Pedido p) throws DataException;
	public boolean update (Connection conn, Pedido p) throws DataException;
}
