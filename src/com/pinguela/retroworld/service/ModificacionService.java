package com.pinguela.retroworld.service;
import java.util.List;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Modificacion;

public interface ModificacionService {
	public List<Modificacion> findByIdEmpleado(Long id) throws DataException;
	public List<Modificacion> findByTipoModificacion(Short idTipo) throws DataException;
	public void create(List<Modificacion> modificaciones) throws DataException;
}
