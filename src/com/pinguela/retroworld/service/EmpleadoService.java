package com.pinguela.retroworld.service;

import java.util.List;

import com.pinguela.DataException;
import com.pinguela.ServiceException;
import com.pinguela.retroworld.model.Empleado;

public interface EmpleadoService {
	public Empleado findById(Long id) throws DataException;
	public Empleado findByEmail(String email) throws DataException;
	public List<Empleado> findByAll() throws DataException;
	public void registrar (Empleado e) throws DataException, ServiceException;
	public Empleado autentificar (String email, String password) throws DataException;
	public boolean update(Empleado e) throws DataException;
	public boolean updatePassword(String password, Long id) throws DataException;
	public boolean delete (Long id) throws DataException;
}
