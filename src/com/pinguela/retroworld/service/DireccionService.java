package com.pinguela.retroworld.service;
import java.util.List;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Direccion;

public interface DireccionService {
	public List<Direccion> findByIdUsuario(Long id) throws DataException;
	public Direccion findById(Long id) throws DataException;
	public List<Direccion> findByAll() throws DataException;
	public void create(Direccion d) throws DataException;
	public boolean update(Direccion d) throws DataException;
	public boolean delete(Long id) throws DataException;
}


