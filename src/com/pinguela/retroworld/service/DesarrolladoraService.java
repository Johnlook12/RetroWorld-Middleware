package com.pinguela.retroworld.service;

import java.util.List;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Desarrolladora;

public interface DesarrolladoraService {
	public List<Desarrolladora> findByAll()throws DataException;
}
