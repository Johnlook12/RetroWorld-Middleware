package com.pinguela.retroworld.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.dao.EstadoPedidoDAO;
import com.pinguela.retroworld.dao.impl.EstadoPedidoDAOImpl;
import com.pinguela.retroworld.model.EstadoPedido;
import com.pinguela.retroworld.service.EstadoPedidoService;
import com.pinguela.retroworld.util.JDBCUtils;

public class EstadoPedidoServiceImpl implements EstadoPedidoService{
	
	private static Logger logger = LogManager.getLogger(EstadoPedidoServiceImpl.class);
	
	private EstadoPedidoDAO estadoPedidoDAO;
	
	public EstadoPedidoServiceImpl() {
		estadoPedidoDAO = new EstadoPedidoDAOImpl();
	}	
	
	@Override
	public List<EstadoPedido> findByAll() throws DataException {
		Connection conn = null;
		List<EstadoPedido> resultados = null;
		boolean commit = false;
		
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultados = estadoPedidoDAO.findByAll(conn);
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
