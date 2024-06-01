package com.pinguela.retroworld.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.dao.ModificacionDAO;
import com.pinguela.retroworld.dao.impl.ModificacionDAOImpl;
import com.pinguela.retroworld.model.Modificacion;
import com.pinguela.retroworld.service.ModificacionService;
import com.pinguela.retroworld.util.JDBCUtils;

public class ModificacionServiceImpl implements ModificacionService{
	private ModificacionDAO modificacionDAO = null;
	private static Logger logger = LogManager.getLogger(ModificacionServiceImpl.class);

	public ModificacionServiceImpl() {
		modificacionDAO = new ModificacionDAOImpl();
	}


	public List<Modificacion> findByIdEmpleado(Long id) throws DataException{
		Connection conn = null;
		List<Modificacion> resultados = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultados = modificacionDAO.findByIdEmpleado(conn, id);
			commit = true;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return resultados;
	}


	public List<Modificacion> findByTipoModificacion(Short idTipo) throws DataException{
		Connection conn = null;
		List<Modificacion> resultados = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultados = modificacionDAO.findByTipoModificacion(conn, idTipo);
			commit = true;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn,commit);
		}
		return resultados;
	}

	@Override
	public List<Modificacion> findByIdAnuncio(Long idAnuncio) throws DataException {
		Connection conn = null;
		List<Modificacion> resultados = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultados = modificacionDAO.findByIdAnuncio(conn, idAnuncio);
			commit = true;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn,commit);
		}
		return resultados;
	}

	public void create(List<Modificacion> modificaciones) throws DataException{
		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			modificacionDAO.create(conn, modificaciones);
			commit = true;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
	}
}