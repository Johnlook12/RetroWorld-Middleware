package com.pinguela.retroworld.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Provincia;

public interface ProvinciaDAO {
	public Provincia findById(Connection conn, Short id) throws DataException;
	public List<Provincia> findByIdPais(Connection conn, Short id) throws DataException;
}
