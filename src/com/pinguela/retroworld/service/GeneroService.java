package com.pinguela.retroworld.service;
import java.util.List;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Genero;

public interface GeneroService {
	public List<Genero> findByAll() throws DataException;
	public List<Genero> findByVideojuego(Long id) throws DataException;
}
