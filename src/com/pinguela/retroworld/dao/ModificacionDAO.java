package com.pinguela.retroworld.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Modificacion;

public interface ModificacionDAO {
	public List<Modificacion> findByIdEmpleado(Connection conn, Long id) throws DataException;
	public List<Modificacion> findByTipoModificacion(Connection conn, Short idTipo) throws DataException;
	public List<Modificacion> findByIdAnuncio(Connection conn, Long idAnuncio) throws DataException;
	public void create(Connection conn,List<Modificacion> modificaciones) throws DataException;
}
