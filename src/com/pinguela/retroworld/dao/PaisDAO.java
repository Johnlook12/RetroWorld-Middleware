package com.pinguela.retroworld.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.retroworld.model.Pais;

public interface PaisDAO {
	public 	List<Pais> findByAll(Connection conn) throws DataException;
	public Pais findById(Connection conn, Integer id) throws DataException;
}
