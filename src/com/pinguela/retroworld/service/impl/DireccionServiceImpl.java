package com.pinguela.retroworld.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.dao.DireccionDAO;
import com.pinguela.retroworld.dao.impl.DireccionDAOImpl;
import com.pinguela.retroworld.model.Direccion;
import com.pinguela.retroworld.service.DireccionService;
import com.pinguela.retroworld.util.JDBCUtils;

public class DireccionServiceImpl implements DireccionService{
	private DireccionDAO direccionDAO = null;
	private static Logger logger = LogManager.getLogger(DireccionServiceImpl.class);
	
	public DireccionServiceImpl() {
		direccionDAO = new DireccionDAOImpl();
	}

	public Direccion findById(Long id) throws DataException{
		Connection conn = null;
		Direccion resultado = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultado = direccionDAO.findById(conn, id);
			commit = true;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return resultado;
	}

	public List<Direccion> findByAll() throws DataException{
		Connection conn = null;
		List<Direccion> resultados= null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultados = direccionDAO.findByAll(conn);
			commit = true;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return resultados;
	}


	public void create(Direccion d) throws DataException{
		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			direccionDAO.create(conn, d);;
			commit = true;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
	}
	
	
	public boolean update(Direccion d) throws DataException{
		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			if(!direccionDAO.update(conn, d)) {
				return false;
			}
			commit = true;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return commit;
	}

	public boolean delete(Long id) throws DataException{
		Connection conn = null;
		boolean commit =  false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			if(!direccionDAO.delete(conn, id)) {
				return false;
			}
			commit = true;
		}catch(SQLException e) {
			logger.error(e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return commit;
	}

	public List<Direccion> findByIdUsuario(Long id) throws DataException{
		Connection conn = null;
		boolean commit = false;
		List<Direccion> direcciones = null;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			direcciones = direccionDAO.findByIdUsuario(conn, id);
			commit = true;
		} catch(SQLException e){
			logger.error(e.getMessage());
			throw new DataException();
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return direcciones;
	}
}