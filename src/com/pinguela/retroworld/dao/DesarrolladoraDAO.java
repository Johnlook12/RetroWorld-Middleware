package com.pinguela.retroworld.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Desarrolladora;

public interface DesarrolladoraDAO {
	public List<Desarrolladora> findByAll(Connection conn)throws DataException;
}
