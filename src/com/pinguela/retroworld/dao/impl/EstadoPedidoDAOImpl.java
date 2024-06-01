package com.pinguela.retroworld.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.retroworld.dao.EstadoPedidoDAO;
import com.pinguela.retroworld.model.EstadoPedido;
import com.pinguela.retroworld.util.JDBCUtils;

public class EstadoPedidoDAOImpl implements EstadoPedidoDAO{

	private static Logger logger = LogManager.getLogger(EstadoPedidoDAOImpl.class);

	public EstadoPedidoDAOImpl() {
	}

	@Override
	public List<EstadoPedido> findByAll(Connection conn) throws DataException {

		List<EstadoPedido> resultados = new ArrayList<EstadoPedido>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT ID, NOMBRE FROM ESTADO_PEDIDO ORDER BY ID ASC", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				resultados.add(loadNext(rs));
			}
		} catch(SQLException e) {
			logger.error(e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
		return resultados;
	}

	protected EstadoPedido loadNext(ResultSet rs) throws SQLException{
		EstadoPedido e = new EstadoPedido();
		int i = 1;

		e.setId(rs.getInt(i++));
		e.setNombre(rs.getString(i++));
		return e;
	}
}
