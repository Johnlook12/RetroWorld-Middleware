package com.pinguela.retroworld.service;
import java.util.List;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Genero;
import com.pinguela.retroworld.model.Idioma;
import com.pinguela.retroworld.model.Plataforma;
import com.pinguela.retroworld.model.Results;
import com.pinguela.retroworld.model.Videojuego;

public interface VideojuegoService {
	public void create(Videojuego v) throws DataException;
	public boolean update(Videojuego v) throws DataException;
	public void asignarIdiomas(Long idVideojuego, List<Integer>idsIdioma) throws DataException;
	public void asignarPlataformas(Long idVideojuego, List<Integer>idsPlataforma) throws DataException;
	public void asignarGeneros(Long idVideojuego, List<Integer>idsGenero) throws DataException;	
	public Results<Videojuego>	findBy(VideojuegoCriteria videojuego, int pos, int pageSize) throws DataException;
	public Videojuego findById(Long id) throws DataException;
}
