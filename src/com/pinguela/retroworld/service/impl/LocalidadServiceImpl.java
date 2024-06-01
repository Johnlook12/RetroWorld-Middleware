package com.pinguela.retroworld.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.dao.LocalidadDAO;
import com.pinguela.retroworld.dao.impl.LocalidadDAOImpl;
import com.pinguela.retroworld.model.Localidad;
import com.pinguela.retroworld.service.LocalidadService;
import com.pinguela.retroworld.util.JDBCUtils;

public class LocalidadServiceImpl implements LocalidadService{
	private LocalidadDAO localidadDAO = null;
	private static Logger logger = LogManager.getLogger(LocalidadServiceImpl.class);
	
	public LocalidadServiceImpl() {
		localidadDAO = new LocalidadDAOImpl();
	}

	
	public List<Localidad> findByIdProvincia(Short id) throws DataException{
		Connection conn = null;
		List<Localidad> resultados = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultados = localidadDAO.findByIdProvincia(conn, id);
			commit = true;
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return resultados;
	}

	
	public Localidad findById(Long id) throws DataException{
		Connection conn = null;
		Localidad resultado = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultado = localidadDAO.findById(conn, id);
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
