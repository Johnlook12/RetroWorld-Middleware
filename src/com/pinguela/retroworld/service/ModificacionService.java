package com.pinguela.retroworld.service;
import java.sql.Connection;
import java.util.List;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Modificacion;

public interface ModificacionService {
	public List<Modificacion> findByIdEmpleado(Long id) throws DataException;
	public List<Modificacion> findByTipoModificacion(Short idTipo) throws DataException;
	public List<Modificacion> findByIdAnuncio(Long idAnuncio) throws DataException;
	public void create(List<Modificacion> modificaciones) throws DataException;
}
