package com.pinguela.retroworld.service;

import java.util.List;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Desarrolladora;

public interface DesarrolladoraService {
	public List<Desarrolladora> findByAll()throws DataException;
}
