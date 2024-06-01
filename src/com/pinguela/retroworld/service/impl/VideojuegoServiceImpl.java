package com.pinguela.retroworld.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.dao.VideojuegoDAO;
import com.pinguela.retroworld.dao.impl.VideojuegoDAOImpl;
import com.pinguela.retroworld.model.Results;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.service.VideojuegoCriteria;
import com.pinguela.retroworld.service.VideojuegoService;
import com.pinguela.retroworld.util.JDBCUtils;

public class VideojuegoServiceImpl implements VideojuegoService{
	private VideojuegoDAO videojuegoDAO = null;
	private static Logger logger = LogManager.getLogger(VideojuegoServiceImpl.class);
	
	public VideojuegoServiceImpl() {
		videojuegoDAO = new VideojuegoDAOImpl();
	}

	
	public void create(Videojuego v) throws DataException{
		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			videojuegoDAO.create(conn, v);
			commit = true;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
	}


	public boolean update(Videojuego v) throws DataException{
		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			if(!videojuegoDAO.update(conn, v)) {
				return false;
			}
			commit = true;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return true;
	}

	public Results<Videojuego> findBy(VideojuegoCriteria videojuego, int pos, int pageSize) throws DataException{
		Connection conn = null;
		Results<Videojuego> resultados = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultados = videojuegoDAO.findBy(conn, videojuego, pos, pageSize);
			commit = true;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return resultados;
	}
	
	public Videojuego findById(Long id) throws DataException{
		Connection conn = null;
		Videojuego resultado = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultado = videojuegoDAO.findById(conn, id);
			commit = true;
		} catch(SQLException e) {
			logger.error(e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return resultado;
	}


	@Override
	public void asignarIdiomas(Long idVideojuego, List<Integer>idsIdioma) throws DataException{
		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			videojuegoDAO.asignarIdioma(conn, idVideojuego, idsIdioma);
			commit = true;
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
	}


	@Override
	public void asignarPlataformas(Long idVideojuego, List<Integer> idsPlataforma) throws DataException{
		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			videojuegoDAO.asignarPlataforma(conn, idVideojuego, idsPlataforma);
			commit = true;
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
	}


	@Override
	public void asignarGeneros(Long idVideojuego, List<Integer> idsGenero) throws DataException{
		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			videojuegoDAO.asignarGenero(conn, idVideojuego, idsGenero);
			commit = true;
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
	}


}
