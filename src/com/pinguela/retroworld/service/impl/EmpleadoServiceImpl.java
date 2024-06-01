package com.pinguela.retroworld.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.pinguela.DataException;
import com.pinguela.ServiceException;
import com.pinguela.retroworld.dao.EmpleadoDAO;
import com.pinguela.retroworld.dao.impl.EmpleadoDAOImpl;
import com.pinguela.retroworld.model.Empleado;
import com.pinguela.retroworld.service.EmpleadoService;
import com.pinguela.retroworld.service.MailService;
import com.pinguela.retroworld.util.JDBCUtils;

public class EmpleadoServiceImpl implements EmpleadoService{
	
	private static Logger logger = LogManager.getLogger(EmpleadoServiceImpl.class);
	private MailService mailService = null;
	private EmpleadoDAO empleadoDAO = null;
	private static final StrongPasswordEncryptor PASSWORD_ENCRYPTOR = new StrongPasswordEncryptor();

	public EmpleadoServiceImpl() {
		mailService = new MailServiceImpl();
		empleadoDAO = new EmpleadoDAOImpl();
	}


	public Empleado findById(Long id) throws DataException{
		Connection conn = null;
		Empleado e= null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			e = empleadoDAO.findById(conn, id);
			commit = true;
		}catch(SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw new DataException(sqle);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return e;
	}


	public Empleado findByEmail(String email) throws DataException{
		Connection conn = null;
		Empleado e = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			e = empleadoDAO.findByEmail(conn, email);
			commit = true;
		}catch(SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw new DataException(sqle);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return e;
	}


	public List<Empleado> findByAll() throws DataException{
		Connection conn = null;
		List<Empleado> empleados = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			empleados = empleadoDAO.findByAll(conn);
			commit = true;
		}catch(SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw new DataException(sqle);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return empleados;
	}


	public void registrar(Empleado e) throws DataException, ServiceException	{
		Connection conn = null;
		boolean commit = false;
		e.setPassword(PASSWORD_ENCRYPTOR.encryptPassword(e.getPassword()));
		try {

			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			empleadoDAO.create(conn, e);			
			mailService.enviar(e.getEmail(), "Bienvenido a nuestra empresa!", "Te vas a cagar...");
			commit = true;
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw new DataException(sqle); 
		} finally {
			JDBCUtils.close(conn, commit);
		}

	}

	public Empleado autentificar(String email, String password) throws DataException{
		Empleado e = null;
		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			e = empleadoDAO.findByEmail(conn, email);

			if(e==null) {
				return null;
			}

			if(!PASSWORD_ENCRYPTOR.checkPassword(password, e.getPassword())) {
				e = null;
			}

			commit = true;
		}catch(SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw new DataException(sqle);
		} finally {
			JDBCUtils.close(conn, commit);
		}

		return e;
	}


	public boolean update(Empleado e) throws DataException{
		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			
			if (!empleadoDAO.update(conn, e)) {
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
	
	public boolean updatePassword(String password, Long id) throws DataException{
		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			if(!empleadoDAO.updatePassword(conn, PASSWORD_ENCRYPTOR.encryptPassword(password), id)) {
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


	public boolean delete(Long id) throws DataException{
		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			if(!empleadoDAO.delete(conn, id)) {
				return false;
			}
			commit = true;
		}catch(SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw new DataException(sqle);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return commit;
	}

}