package com.pinguela.retroworld.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.retroworld.model.Plataforma;

public interface PlataformaDAO {
	public List<Plataforma> findByAll(Connection conn) throws DataException;
	public List<Plataforma> findByVideojuego(Connection conn, Long idVideojuego) throws DataException;
}
	