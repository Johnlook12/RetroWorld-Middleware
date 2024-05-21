package com.pinguela.retroworld.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.dao.UsuarioDAO;
import com.pinguela.retroworld.dao.impl.UsuarioDAOImpl;
import com.pinguela.retroworld.model.Usuario;
import com.pinguela.retroworld.service.MailService;
import com.pinguela.retroworld.service.UsuarioService;
import com.pinguela.retroworld.util.JDBCUtils;

public class UsuarioServiceImpl implements UsuarioService{
	public static final StrongPasswordEncryptor PASSWORD_ENCRYPTOR= new StrongPasswordEncryptor();
	private static Logger logger = LogManager.getLogger(UsuarioServiceImpl.class);
	private UsuarioDAO usuarioDAO = null;
	private MailService mailService = null;

	public UsuarioServiceImpl() {
		usuarioDAO = new UsuarioDAOImpl();
		mailService = new MailServiceImpl();
	}




	public Usuario autentificar(String email, String password) throws DataException{
		Usuario user = null;
		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			user = usuarioDAO.findByEmail(conn, email);
			if(user==null) {
				return null;
			}

			if(!PASSWORD_ENCRYPTOR.checkPassword(password, user.getPassword())) {
				user = null;
			}

			commit = true;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return user;
	}

	public void registrar(Usuario u) throws DataException{

		u.setPassword(PASSWORD_ENCRYPTOR.
				encryptPassword(u.getPassword()));

		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			usuarioDAO.create(conn, u);
			mailService.enviar(u.getEmail(),"Hola", "Hola que tal");
			commit = true;

		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}

	}


	public boolean update(Usuario u) throws DataException{
		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			usuarioDAO.update(conn, u);
			commit = true;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return commit;
	}

	public boolean updatePassword(String password, Long id) throws DataException{
		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			if(!usuarioDAO.updatePassword(conn, PASSWORD_ENCRYPTOR.encryptPassword(password), id)) {
				return false;
			}
			commit = true;
		} catch(SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw new DataException(sqle);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return commit;
	}
	
	public boolean delete (Long id) throws DataException{
		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			usuarioDAO.softDelete(conn, id);
			commit = true;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return commit;
	}




	
	public Usuario findById(Long id) throws DataException {
		Connection conn = null;
		boolean commit = false;
		Usuario u = null;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			u = usuarioDAO.findById(conn, id);
			commit = true;
		} catch(SQLException e) {
			logger.error(e.getMessage());
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return u;
	}




	
	public Usuario findByEmail(String email) throws DataException {
		Connection conn = null;
		boolean commit = false;
		Usuario u = null;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			u = usuarioDAO.findByEmail(conn, email);
			commit = true;
		} catch(SQLException e) {
			logger.error(e.getMessage());
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return u;
	}




	
	public List<Usuario> findByAll() throws DataException {
		Connection conn = null;
		boolean commit = false;
		List<Usuario> usuarios = null;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			usuarios = usuarioDAO.findByAll(conn);
			commit = true;
		} catch(SQLException e) {
			logger.error(e.getMessage());
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return usuarios;
	}

}
