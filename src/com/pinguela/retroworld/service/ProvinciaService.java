package com.pinguela.retroworld.service;
import java.util.List;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Provincia;

public interface ProvinciaService {
	public Provincia findById(Short id) throws DataException;
	public List<Provincia> findByIdPais(Short id) throws DataException;
}
