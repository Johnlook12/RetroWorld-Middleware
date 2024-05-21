package com.pinguela.retroworld.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.dao.ProvinciaDAO;
import com.pinguela.retroworld.dao.impl.ProvinciaDAOImpl;
import com.pinguela.retroworld.model.Provincia;
import com.pinguela.retroworld.service.ProvinciaService;
import com.pinguela.retroworld.util.JDBCUtils;

public class ProvinciaServiceImpl implements ProvinciaService{
	private ProvinciaDAO provinciaDAO = null;
	private static Logger logger = LogManager.getLogger(ProvinciaServiceImpl.class);
	
	public ProvinciaServiceImpl() {
		provinciaDAO = new ProvinciaDAOImpl();
	}

	
	public Provincia findById(Short id) throws DataException{
		Connection conn = null;
		Provincia resultado = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultado = provinciaDAO.findById(conn, id);
			commit = true;
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return resultado;
	}

	
	public List<Provincia> findByIdPais(Short id) throws DataException{
		Connection conn = null;
		List<Provincia> resultados = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultados = provinciaDAO.findByIdPais(conn, id);
			commit = true;
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return resultados;
	}

}
