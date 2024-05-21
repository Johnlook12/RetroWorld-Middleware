package com.pinguela.retroworld.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.dao.PlataformaDAO;
import com.pinguela.retroworld.dao.impl.PlataformaDAOImpl;
import com.pinguela.retroworld.model.Plataforma;
import com.pinguela.retroworld.service.PlataformaService;
import com.pinguela.retroworld.util.JDBCUtils;

public class PlataformaServiceImpl implements PlataformaService{
	private static Logger logger = LogManager.getLogger(PlataformaServiceImpl.class);
	private PlataformaDAO plataformaDAO = null;	
	
	public PlataformaServiceImpl() {
		plataformaDAO = new PlataformaDAOImpl();
	}
	
	public List<Plataforma> findByAll() throws DataException{
		Connection conn = null;
		List<Plataforma> resultados = null;
		boolean commit = false;
		
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultados = plataformaDAO.findByAll(conn);
			commit = true;
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return resultados;
	}
	
	public List<Plataforma> findByVideojuego(Long id)throws DataException{
		Connection conn = null;
		List<Plataforma> resultados = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultados = plataformaDAO.findByVideojuego(conn, id);
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
