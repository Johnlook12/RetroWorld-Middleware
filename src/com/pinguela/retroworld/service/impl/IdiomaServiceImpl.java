package com.pinguela.retroworld.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.dao.IdiomaDAO;
import com.pinguela.retroworld.dao.impl.IdiomaDAOImpl;
import com.pinguela.retroworld.model.Idioma;
import com.pinguela.retroworld.service.IdiomaService;
import com.pinguela.retroworld.util.JDBCUtils;

public class IdiomaServiceImpl implements IdiomaService{
	private static Logger logger = LogManager.getLogger(IdiomaServiceImpl.class);
	private IdiomaDAO idiomaDAO = null;
	
	public IdiomaServiceImpl() {
		idiomaDAO = new IdiomaDAOImpl();
	}

	public List<Idioma> findByAll() throws DataException {
		Connection conn = null;
		List<Idioma> resultados = null;
		boolean commit = false;
		
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultados = idiomaDAO.findByAll(conn);
			commit = true;
		} catch(SQLException e) {
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return resultados;
	}
	
	public List<Idioma> findByVideojuego(Long id)throws DataException{
		Connection conn = null;
		List<Idioma> resultados = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultados = idiomaDAO.findByVideojuego(conn, id);
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
