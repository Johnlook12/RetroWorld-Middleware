package com.pinguela.retroworld.service;

import java.util.List;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Idioma;

public interface IdiomaService {
	public List<Idioma> findByAll() throws DataException;
	public List<Idioma> findByVideojuego(Long id) throws DataException;
}
