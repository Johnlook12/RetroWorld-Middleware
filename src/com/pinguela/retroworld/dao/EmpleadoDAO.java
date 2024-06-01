package com.pinguela.retroworld.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Empleado;

public interface EmpleadoDAO {
	
	public Empleado findById(Connection conn, Long id) throws DataException;
	public Empleado findByEmail(Connection conn, String email) throws DataException;
	public List<Empleado> findByAll(Connection conn) throws DataException;	
	public void create(Connection conn, Empleado empleado) throws DataException;
	public boolean update(Connection conn, Empleado empleado) throws DataException;
	public boolean updatePassword(Connection conn, String password, Long id) throws DataException;
	public boolean delete(Connection conn, Long id) throws DataException;

}
