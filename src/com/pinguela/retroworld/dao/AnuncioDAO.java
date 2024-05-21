package com.pinguela.retroworld.dao;

import java.sql.Connection;

import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.model.Results;
import com.pinguela.retroworld.service.AnuncioCriteria;

public interface AnuncioDAO {
	public Results<Anuncio> findBy(Connection conn, AnuncioCriteria criteria, int pos, int pageSize) throws DataException;
	public Anuncio findById(Connection conn, Long id) throws DataException;
	public void create(Connection conn, Anuncio a) throws DataException;
	public boolean update(Connection conn, Anuncio a) throws DataException;
	public boolean softDelete (Connection conn, Long idAnuncio) throws DataException;
	}
