package com.pinguela.retroworld.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Genero;

public interface GeneroDAO {
	public List<Genero> findByAll(Connection conn) throws DataException;
	public List<Genero> findByVideojuego(Connection conn, Long idVideojuego) throws DataException;
}
