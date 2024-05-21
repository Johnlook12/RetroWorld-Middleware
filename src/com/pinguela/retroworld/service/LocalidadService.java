package com.pinguela.retroworld.service;
import java.util.List;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Localidad;

public interface LocalidadService {
	public List<Localidad> findByIdProvincia(Short id) throws DataException;
	public Localidad findById(Long id) throws DataException;
}
