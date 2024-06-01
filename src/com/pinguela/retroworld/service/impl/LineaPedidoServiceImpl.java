package com.pinguela.retroworld.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.dao.LineaPedidoDAO;
import com.pinguela.retroworld.dao.impl.LineaPedidoDAOImpl;
import com.pinguela.retroworld.model.LineaPedido;
import com.pinguela.retroworld.service.LineaPedidoService;
import com.pinguela.retroworld.util.JDBCUtils;

public class LineaPedidoServiceImpl implements LineaPedidoService{
	
	private static Logger logger = LogManager.getLogger(LineaPedidoServiceImpl.class);
	private LineaPedidoDAO lineaPedidoDAO = null;
	
	public LineaPedidoServiceImpl() {
		lineaPedidoDAO = new LineaPedidoDAOImpl();
	}

	
	public LineaPedido findById(Long id) throws DataException{
		Connection conn = null;
		LineaPedido resultado = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultado = lineaPedidoDAO.findById(conn, id);
			commit = true;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn,commit);
		}
		return resultado;
	}
}
