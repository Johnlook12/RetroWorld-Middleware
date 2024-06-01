package com.pinguela.retroworld.service;

import java.util.List;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.EstadoVideojuego;

public interface EstadoVideojuegoService {
	public List<EstadoVideojuego> findByAll()throws DataException;
}
