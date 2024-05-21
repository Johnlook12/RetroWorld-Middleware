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
import com.pinguela.retroworld.dao.PedidoDAO;
import com.pinguela.retroworld.model.LineaPedido;
import com.pinguela.retroworld.model.Pedido;
import com.pinguela.retroworld.util.JDBCUtils;

public class PedidoDAOImpl implements PedidoDAO{
	
	private LineaPedidoDAO lineaPedidoDAO = null;
	private static Logger logger = LogManager.getLogger(PedidoDAOImpl.class);
	
	public PedidoDAOImpl() {
		lineaPedidoDAO = new LineaPedidoDAOImpl();
	}
	
	
	
	public void create(Connection conn, Pedido p) throws DataException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement(" INSERT INTO PEDIDO (FECHA, USUARIO_ID, PRECIO_TOTAL, ESTADO_PEDIDO_ID)"
											+" VALUES(?,?,?,?)"
											, Statement.RETURN_GENERATED_KEYS);
			
			logger.info(pstmt);
			
			int i =1;
			
			pstmt.setDate(i++, new java.sql.Date(p.getFecha().getTime()));
			pstmt.setLong(i++, p.getIdUsuario());
			pstmt.setDouble(i++, p.getPrecioTotal());
			pstmt.setInt(i++, p.getIdEstado());
			
			int insertedRows = pstmt.executeUpdate();
			
			if(insertedRows!=1) {
				// throw new ...Exception
			}
			
			rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				Long id = rs.getLong(1);
				p.setId(id);
			}
			
			lineaPedidoDAO.create(conn, p.getId(), p.getLineas());
			
			
		} catch(SQLException e) {
			logger.error("Pedido: "+p, e);
			throw new DataException(e);
			} finally {
				JDBCUtils.close(pstmt, rs);
			}	
	}
	
	
	
	public boolean update(Connection conn, Pedido p) throws DataException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(" UPDATE PEDIDO SET"
										  +" FECHA = ?,"
										  +" USUARIO_ID = ?,"
										  +" PRECIO_TOTAL = ?,"
										  +" ESTADO_PEDIDO_ID = ?"
										  +" WHERE ID = ?");
			
			logger.info(pstmt);
			
			int i =1;
			pstmt.setDate(i++, new java.sql.Date(p.getFecha().getTime()));
			pstmt.setLong(i++, p.getIdUsuario());
			pstmt.setDouble(i++, p.getPrecioTotal());
			pstmt.setInt(i++, p.getIdEstado());
			pstmt.setLong(i++, p.getId());
			
			int updatedRows = pstmt.executeUpdate();
			
			lineaPedidoDAO.deleteByPedido(conn, p.getId());
			lineaPedidoDAO.create(conn, p.getId(), p.getLineas());
			
			if(updatedRows!=1) {
				return false;
			}
			
		} catch(SQLException e) {
			logger.error("Pedido: "+p);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt);
		}
		return true;
	}

	
	public Pedido findById(Connection conn, Long id) throws DataException{
		Pedido resultado = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			StringBuilder query = new StringBuilder(" SELECT P.ID, P.FECHA, U.NOMBRE, P.USUARIO_ID, PRECIO_TOTAL, ESTADO_PEDIDO_ID");
			query.append(" FROM PEDIDO P");
			query.append(" INNER JOIN USUARIO U ON U.ID = P.USUARIO_ID");
			query.append(" WHERE P.ID = ?");
			
			String sql = query.toString();
			
			logger.info(sql);
			
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			
			pstmt.setLong(i++, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultado = loadNext(conn, rs);
			}
					
			
		}catch(SQLException e) {
			logger.error("ID: "+id, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
		
		return resultado;
	}

	public List<Pedido> findByUsuario(Connection conn, Long idUsuario) throws DataException{
		List<Pedido> resultados = new ArrayList<Pedido>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			
			StringBuilder query = new StringBuilder(" SELECT P.ID, P.FECHA, U.NOMBRE, P.USUARIO_ID, PRECIO_TOTAL, ESTADO_PEDIDO_ID");
			query.append(" FROM PEDIDO P");
			query.append(" INNER JOIN USUARIO U ON U.ID = P.USUARIO_ID");
			query.append(" WHERE U.ID = ?");
			query.append(" ORDER BY P.FECHA DESC");
			
			String sql = query.toString();
			
			logger.info(sql);
			
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			
			pstmt.setLong(i++, idUsuario);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultados.add(loadNext(conn,rs));
			}
			
		}catch(SQLException e) {
			logger.error("Usuario ID: "+idUsuario, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
		
		return resultados;
	}
	
	protected Pedido loadNext(Connection conn, ResultSet rs) throws SQLException, DataException {
		Pedido p = new Pedido();
		int i = 1;
		
		p.setId(rs.getLong(i++));
		p.setFecha(rs.getDate(i++));
		p.setNombreUsuario(rs.getString(i++));
		p.setIdUsuario(rs.getLong(i++));
		p.setPrecioTotal(rs.getDouble(i++));
		p.setIdEstado(rs.getInt(i++));
		
		List<LineaPedido> lineas = lineaPedidoDAO.findByIdPedido(conn, p.getId());
		p.setLineas(lineas);
		
		return p;
		
	}	
}
