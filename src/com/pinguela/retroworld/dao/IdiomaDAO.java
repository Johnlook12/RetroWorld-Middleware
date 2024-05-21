package com.pinguela.retroworld.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.retroworld.model.Idioma;

public interface IdiomaDAO {
	public List<Idioma> findByAll(Connection conn) throws DataException;
	public List<Idioma> findByVideojuego(Connection conn, Long idVideojuego) throws DataException;
}
