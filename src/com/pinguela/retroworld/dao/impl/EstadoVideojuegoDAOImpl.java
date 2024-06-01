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
import com.pinguela.retroworld.dao.EstadoVideojuegoDAO;
import com.pinguela.retroworld.model.EstadoVideojuego;
import com.pinguela.retroworld.util.JDBCUtils;

public class EstadoVideojuegoDAOImpl implements EstadoVideojuegoDAO{

	private static Logger logger = LogManager.getLogger(EstadoVideojuegoDAOImpl.class);

	public EstadoVideojuegoDAOImpl() {
	}

	@Override
	public List<EstadoVideojuego> findByAll(Connection conn) throws DataException {

		List<EstadoVideojuego> resultados = new ArrayList<EstadoVideojuego>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT ID, NOMBRE, DESCRIPCION FROM ESTADO_VIDEOJUEGO ORDER BY ID ASC", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

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
	
	protected EstadoVideojuego loadNext(ResultSet rs) throws SQLException{
		EstadoVideojuego e = new EstadoVideojuego();
		int i = 1;
		
		e.setId(rs.getInt(i++));
		e.setNombre(rs.getString(i++));
		e.setDescripcion(rs.getString(i++));
		
		return e;
	}
}
