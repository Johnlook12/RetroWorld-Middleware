package com.pinguela.retroworld.service;
import java.util.List;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Pais;

public interface PaisService {
	public 	List<Pais> findByAll() throws DataException;
	public Pais findById(Integer id) throws DataException;
}
