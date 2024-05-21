package com.pinguela.retroworld.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.dao.LineaPedidoDAO;
import com.pinguela.retroworld.model.LineaPedido;
import com.pinguela.retroworld.util.JDBCUtils;

public class LineaPedidoDAOImpl implements LineaPedidoDAO{
	
	private static Logger logger = LogManager.getLogger(LineaPedidoDAOImpl.class);
	
	public LineaPedidoDAOImpl() {
		
	}
	
	
	public void create(Connection conn, Long idPedido, List<LineaPedido> lineas) throws DataException{
		if (lineas.size()==0) {
			return;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			StringBuilder query = new StringBuilder("INSERT INTO LINEA_PEDIDO(PRECIO, PEDIDO_ID, VIDEOJUEGO_ID)");
			query.append("VALUES");
			
			JDBCUtils.appendMultipleInsertParameters(query, "(?,?,?)", lineas.size());
			
			pstmt = conn.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			
			logger.info(query.toString());
			
			int i = 1;
			for(LineaPedido linea:lineas) {
				pstmt.setDouble(i++, linea.getPrecio());
				linea.setIdPedido(idPedido);
				pstmt.setLong(i++, linea.getIdPedido());
				pstmt.setLong(i++, linea.getIdVideojuego());
			}
			
			int insertedRows = pstmt.executeUpdate();
			
			if(insertedRows != lineas.size()) {
				// trows new Exception
			}
			
			rs = pstmt.getGeneratedKeys();
			i = 0;
			Long lineaPedidoId = null;
			while(rs.next()) {
				lineaPedidoId = rs.getLong(1);
				lineas.get(i++).setId(lineaPedidoId);
			}
					
		}catch(SQLException e) {
			logger.error("Pedido ID: "+idPedido+" lineas: "+lineas);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
	}
	
	
	public boolean deleteByPedido(Connection conn, Long idPedido) throws DataException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(" DELETE FROM LINEA_PEDIDO WHERE PEDIDO_ID = ?");
			int i=1;
			
			logger.info(pstmt);
			
			pstmt.setLong(i++, idPedido);
			
			int deleteRows = pstmt.executeUpdate();
			
			if(deleteRows==0) {
				return false;
			}
		} catch(SQLException e) {
			logger.error("Pedido ID: "+idPedido, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt);
		}
		return true;
	}

	
	public LineaPedido findById(Connection conn, Long id) throws DataException{
		LineaPedido resultado = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			StringBuilder query = new StringBuilder(" SELECT LP.ID, V.NOMBRE, LP.PRECIO, LP.VIDEOJUEGO_ID, LP.PEDIDO_ID");
			query.append(" FROM LINEA_PEDIDO LP");
			query.append(" INNER JOIN PEDIDO P ON P.ID = LP.PEDIDO_ID");
			query.append(" INNER JOIN VIDEOJUEGO V ON V.ID = LP.VIDEOJUEGO_ID");
			query.append(" WHERE LP.ID = ?");
			
			String sql = query.toString();
			
			logger.info(sql);
			
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			
			pstmt.setLong(i++, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultado = loadNext(rs);
			}
			
		}catch(SQLException e) {
			logger.error("ID : "+id, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
		
		
		
		return resultado;
	}

	
	public List<LineaPedido> findByIdPedido(Connection conn, Long idPedido) throws DataException{
		List<LineaPedido> resultados = new ArrayList<LineaPedido>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			StringBuilder query = new StringBuilder(" SELECT LP.ID, V.NOMBRE, LP.PRECIO, LP.VIDEOJUEGO_ID, LP.PEDIDO_ID");
			query.append(" FROM LINEA_PEDIDO LP");
			query.append(" INNER JOIN PEDIDO P ON P.ID = LP.PEDIDO_ID");
			query.append(" INNER JOIN VIDEOJUEGO V ON V.ID = LP.VIDEOJUEGO_ID");
			query.append(" WHERE P.ID = ?");
			query.append(" ORDER BY LP.ID ASC");
			
			String sql = query.toString();
			
			logger.info(sql);
			
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			
			pstmt.setLong(i++, idPedido);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultados.add(loadNext(rs));
			}
			
			
		} catch(SQLException e) {
			logger.error("Pedido ID: "+idPedido, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
		return resultados;
	}


	protected LineaPedido loadNext(ResultSet rs) throws SQLException{
		LineaPedido l = new LineaPedido();
		int i =1;
		
		l.setId(rs.getLong(i++));
		l.setNombreVideojuego(rs.getString(i++));
		l.setPrecio(rs.getDouble(i++));
		l.setIdVideojuego(rs.getLong(i++));
		l.setIdPedido(rs.getLong(i++));
		
		return l;
	}
	
}
