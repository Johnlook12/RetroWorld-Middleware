package com.pinguela.retroworld.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.retroworld.model.Results;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.service.VideojuegoCriteria;

public interface VideojuegoDAO {
	public void create(Connection conn, Videojuego v) throws DataException;
	public boolean update(Connection conn, Videojuego v) throws DataException;
	public Results<Videojuego>	findBy(Connection conn, VideojuegoCriteria videojuego, int pos, int pageSize) throws DataException;
	public Videojuego findById(Connection conn, Long id) throws DataException;
	public void asignarIdioma(Connection conn, Long idVideojuego, List<Integer> idsIdiomas) throws DataException;
	public void asignarPlataforma(Connection conn, Long idVideojuego, List<Integer> idsPlataformas) throws DataException;
	public void asignarGenero(Connection conn, Long idVideojuego, List<Integer> idsGeneros) throws DataException;
}
