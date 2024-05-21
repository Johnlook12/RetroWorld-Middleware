package com.pinguela.retroworld.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.retroworld.model.Usuario;

public interface UsuarioDAO {
	public Usuario findById(Connection conn, Long id) throws DataException;
	public Usuario findByEmail(Connection conn, String email) throws DataException;
	public List<Usuario> findByAll(Connection conn) throws DataException;
	public void create (Connection conn, Usuario u) throws DataException;
	public boolean update (Connection conn, Usuario u) throws DataException;
	public boolean updatePassword(Connection conn, String password, Long id) throws DataException;
	public boolean softDelete(Connection conn, Long id) throws DataException;

}
