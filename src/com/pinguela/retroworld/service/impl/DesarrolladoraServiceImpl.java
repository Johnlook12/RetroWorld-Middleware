package com.pinguela.retroworld.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.dao.DesarrolladoraDAO;
import com.pinguela.retroworld.dao.impl.DesarrolladoraDAOImpl;
import com.pinguela.retroworld.model.Desarrolladora;
import com.pinguela.retroworld.service.DesarrolladoraService;
import com.pinguela.retroworld.util.JDBCUtils;

public class DesarrolladoraServiceImpl implements DesarrolladoraService{
	
	private static Logger logger = LogManager.getLogger(DesarrolladoraServiceImpl.class);
	private DesarrolladoraDAO desarrolladoraDAO = null;
	
	public DesarrolladoraServiceImpl() {
		desarrolladoraDAO = new DesarrolladoraDAOImpl();
	}
	
	@Override
	public List<Desarrolladora> findByAll() throws DataException {
		Connection conn = null;
		List<Desarrolladora> resultados = null;
		boolean commit = false;
		
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultados = desarrolladoraDAO.findByAll(conn);
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
