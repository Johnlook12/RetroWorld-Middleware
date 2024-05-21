package com.pinguela.retroworld.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.dao.PedidoDAO;
import com.pinguela.retroworld.dao.impl.PedidoDAOImpl;
import com.pinguela.retroworld.model.LineaPedido;
import com.pinguela.retroworld.model.Pedido;
import com.pinguela.retroworld.service.PedidoService;
import com.pinguela.retroworld.util.JDBCUtils;

public class PedidoServiceImpl implements PedidoService{

	private PedidoDAO pedidoDAO = null;
	private static Logger logger = LogManager.getLogger(PedidoServiceImpl.class);
	
	public PedidoServiceImpl() {
		pedidoDAO = new PedidoDAOImpl();
	}
	
	public void create(Pedido p) throws DataException{
		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			p.setPrecioTotal(calculatePrecio(p));
			pedidoDAO.create(conn, p);
			commit = true;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
	}
	
	
	public Double calculatePrecio(Pedido p) {
		Double precioTotal = 0.0d;
		
		for(LineaPedido lp:p.getLineas()) {
			precioTotal+=lp.getPrecio();
		}

		return precioTotal;
	}


	public Pedido findById(Long id) throws DataException{
		Connection conn = null;
		Pedido resultado = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultado = pedidoDAO.findById(conn, id);
			commit = true;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return resultado;
	}


	public List<Pedido> findByUsuario(Long idUsuario) throws DataException{
		Connection conn = null;
		List<Pedido> resultados = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			resultados = pedidoDAO.findByUsuario(conn, idUsuario);
			commit = true;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return resultados;
	}


	public boolean update(Pedido p) throws DataException{
		Connection conn = null;
		boolean commit = false;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			p.setPrecioTotal(calculatePrecio(p));
			if(!pedidoDAO.update(conn, p)) {
				return false;
			}
			commit = true;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(conn, commit);
		}
		return true;
	}

}
