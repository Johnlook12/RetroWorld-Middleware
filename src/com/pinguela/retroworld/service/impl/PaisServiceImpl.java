package com.pinguela.retroworld.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.dao.PaisDAO;
import com.pinguela.retroworld.dao.impl.PaisDAOImpl;
import com.pinguela.retroworld.model.Pais;
import com.pinguela.retroworld.service.PaisService;
import com.pinguela.retroworld.util.JDBCUtils;

public class PaisServiceImpl implements PaisService{
	private PaisDAO paisDAO = null;
	private static Logger logger = LogManager.getLogger(PaisServiceImpl.class);
	
	public PaisServiceImpl() {
		paisDAO = new PaisDAOImpl();
	}

	
	public List<Pais> findByAll() throws DataException{
		Connection conn = null;
		List<Pais> resultados = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultados = paisDAO.findByAll(conn);
			commit = true;
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return resultados;
	}

	public Pais findById(Integer id) throws DataException{
		Connection conn = null;
		Pais resultado = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultado = paisDAO.findById(conn, id);
			commit = true;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return resultado;
	}

}
