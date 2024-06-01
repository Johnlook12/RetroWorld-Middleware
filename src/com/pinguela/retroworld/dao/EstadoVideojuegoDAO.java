package com.pinguela.retroworld.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.EstadoVideojuego;

public interface EstadoVideojuegoDAO {
	public List<EstadoVideojuego> findByAll(Connection conn)throws DataException;
}
