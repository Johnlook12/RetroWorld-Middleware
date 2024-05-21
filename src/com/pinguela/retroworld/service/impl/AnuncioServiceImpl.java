package com.pinguela.retroworld.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.AnuncioDAO;
import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.dao.ModificacionDAO;
import com.pinguela.retroworld.dao.impl.AnuncioDAOImpl;
import com.pinguela.retroworld.dao.impl.ModificacionDAOImpl;
import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.model.Modificacion;
import com.pinguela.retroworld.model.Results;
import com.pinguela.retroworld.service.AnuncioCriteria;
import com.pinguela.retroworld.service.AnuncioService;
import com.pinguela.retroworld.util.JDBCUtils;

public class AnuncioServiceImpl implements AnuncioService{

	private AnuncioDAO anuncioDAO = null;
	private ModificacionDAO modificacionDAO = null;
	private static Logger logger = LogManager.getLogger(AnuncioServiceImpl.class);

	public AnuncioServiceImpl() {
		anuncioDAO = new AnuncioDAOImpl();
		modificacionDAO = new ModificacionDAOImpl();
	}


	public Results<Anuncio> findBy(AnuncioCriteria criteria, int pos, int pageSize) throws DataException{
		Connection conn = null;
		Results<Anuncio> resultados = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultados = anuncioDAO.findBy(conn, criteria, pos, pageSize);
			commit = true;
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}

		return resultados;
	}
	
	public Anuncio findById(Long id) throws DataException{
		Connection conn = null;
		Anuncio a = null;
		boolean commit = false;
	
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			a = anuncioDAO.findById(conn, id);
			commit = true;
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		
		return a;
	}

	public void create(Anuncio a) throws DataException{
		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();

			conn.setAutoCommit(false);

			anuncioDAO.create(conn, a);

			commit = true;

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException();
		} finally {
			JDBCUtils.close(conn, commit);
		}		
	}

	
	public boolean update(Anuncio a, List<Modificacion> modificaciones) throws DataException{
		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();

			conn.setAutoCommit(false);
			if(!anuncioDAO.update(conn, a)) {
				return false;
			}
			if (!modificaciones.isEmpty()) {
				modificacionDAO.create(conn, modificaciones);	
			}
			commit = true;

		} catch(SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw new DataException();
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return commit;
	}


	public boolean delete(Long idAnuncio) throws DataException{
		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			if(!anuncioDAO.softDelete(conn, idAnuncio)) {
				return false;
			}
			commit = true;
		} catch(SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw new DataException();
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return commit;
	}

}
