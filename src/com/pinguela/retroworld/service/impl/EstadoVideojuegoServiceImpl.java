package com.pinguela.retroworld.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.dao.EstadoVideojuegoDAO;
import com.pinguela.retroworld.dao.impl.EstadoVideojuegoDAOImpl;
import com.pinguela.retroworld.model.EstadoVideojuego;
import com.pinguela.retroworld.service.EstadoVideojuegoService;
import com.pinguela.retroworld.util.JDBCUtils;

public class EstadoVideojuegoServiceImpl implements EstadoVideojuegoService{
	
	private static Logger logger = LogManager.getLogger(EstadoVideojuegoServiceImpl.class);
	private EstadoVideojuegoDAO estadoVideojuegoDAO;
	
	public EstadoVideojuegoServiceImpl() {
		estadoVideojuegoDAO = new EstadoVideojuegoDAOImpl();
	}
	
	@Override
	public List<EstadoVideojuego> findByAll() throws DataException {
		Connection conn = null;
		List<EstadoVideojuego> resultados = null;
		boolean commit = false;
		
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultados = estadoVideojuegoDAO.findByAll(conn);
			commit = true;
		} catch(SQLException e) {
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return resultados;
	}

}
