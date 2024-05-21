package com.pinguela.retroworld.service;

import java.util.List;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.model.Modificacion;
import com.pinguela.retroworld.model.Results;

public interface AnuncioService {
	
	public Results<Anuncio> findBy(AnuncioCriteria criteria, int pos, int pageSize) throws DataException;
	public Anuncio findById(Long id) throws DataException;
	public void create(Anuncio a) throws DataException;
	public boolean update(Anuncio a,List<Modificacion> modificaciones) throws DataException;
	public boolean delete (Long idAnuncio) throws DataException;

}
