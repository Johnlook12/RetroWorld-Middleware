package com.pinguela.retroworld.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.retroworld.model.Localidad;

public interface LocalidadDAO {
	
	public List<Localidad> findByIdProvincia(Connection conn, Short id) throws DataException;
	public Localidad findById(Connection conn, Long id) throws DataException;
	
}
