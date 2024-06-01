package com.pinguela.retroworld.service;
import java.util.List;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Genero;
import com.pinguela.retroworld.model.Plataforma;

public interface PlataformaService {
	public List<Plataforma> findByAll() throws DataException;
	public List<Plataforma> findByVideojuego(Long id) throws DataException;
}
