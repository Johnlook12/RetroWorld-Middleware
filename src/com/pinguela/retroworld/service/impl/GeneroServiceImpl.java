package com.pinguela.retroworld.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.dao.GeneroDAO;
import com.pinguela.retroworld.dao.impl.GeneroDAOImpl;
import com.pinguela.retroworld.model.Genero;
import com.pinguela.retroworld.service.GeneroService;
import com.pinguela.retroworld.util.JDBCUtils;

public class GeneroServiceImpl implements GeneroService{
	private static Logger logger = LogManager.getLogger(GeneroServiceImpl.class);
	private GeneroDAO generoDAO = null;
	
	public GeneroServiceImpl() {
		generoDAO = new GeneroDAOImpl();
	}
	
	public List<Genero> findByAll() throws DataException {
		Connection conn = null;
		List<Genero> resultados = null;
		boolean commit = false;
		
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultados = generoDAO.findByAll(conn);
			commit = true;
		} catch(SQLException e) {
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return resultados;
	}
	
	public List<Genero> findByVideojuego(Long id)throws DataException{
		Connection conn = null;
		List<Genero> resultados = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultados = generoDAO.findByVideojuego(conn, id);
			commit = true;
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(conn,commit);
		}
		return resultados;
	}
}
