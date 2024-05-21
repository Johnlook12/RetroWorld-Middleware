package com.pinguela.retroworld.service;

import java.util.List;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Usuario;

public interface UsuarioService {
	public Usuario findById(Long id) throws DataException;
	public Usuario findByEmail(String email) throws DataException;
	public List<Usuario> findByAll() throws DataException;
	public void registrar(Usuario u) throws DataException;
	public Usuario autentificar(String email, String password) throws DataException;
	public boolean update(Usuario u) throws DataException;
	public boolean updatePassword(String password, Long id) throws DataException;
	public boolean delete(Long id) throws DataException;
}
