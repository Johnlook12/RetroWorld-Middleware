package com.pinguela.retroworld.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.retroworld.model.Direccion;

public interface DireccionDAO {
	public List<Direccion> findByIdUsuario(Connection conn, Long id) throws DataException;
	public Direccion findByIdEmpleado(Connection conn, Long id) throws DataException;
	public Direccion findById(Connection conn, Long id) throws DataException;
	public List<Direccion> findByAll(Connection conn) throws DataException;
	public void create(Connection conn, Direccion d) throws DataException;
	public boolean update(Connection conn, Direccion d) throws DataException;
	public boolean delete(Connection conn, Long id) throws DataException;
	public boolean deleteByEmpleado(Connection conn, Long idEmpleado) throws DataException;
	public boolean deleteByUsuario(Connection conn, Long idUsuario) throws DataException;
}
