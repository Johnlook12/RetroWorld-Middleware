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
import com.pinguela.retroworld.dao.PaisDAO;
import com.pinguela.retroworld.model.Pais;
import com.pinguela.retroworld.util.JDBCUtils;

public class PaisDAOImpl implements PaisDAO{
	
	private static Logger logger = LogManager.getLogger(PaisDAOImpl.class);
	
	public PaisDAOImpl() {
		
	}
	
	public Pais findById(Connection conn, Integer id) throws DataException{
		Pais resultado = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			StringBuilder query = new StringBuilder (" SELECT ID, NOMBRE, ISO2, ISO3, TELEFONO_CODE");
			query.append(" FROM PAIS");
			query.append(" WHERE ID = ?");
			
			String sql = query.toString();
			
			logger.info(sql);
			
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			
			pstmt.setLong(i++, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultado=loadNext(rs);
			}
			
			
		} catch(SQLException e) {
			logger.error("ID: "+id, e);
			throw new DataException(e);
		} finally{
			JDBCUtils.close(pstmt, rs);
		}
		
		return resultado;
		
	}
	
	public List<Pais> findByAll(Connection conn) throws DataException{
		List<Pais> resultados = new ArrayList<Pais>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			StringBuilder query = new StringBuilder(" SELECT ID, NOMBRE, ISO2, ISO3, TELEFONO_CODE");
			query.append(" FROM PAIS");
			query.append(" ORDER BY NOMBRE ASC");
			
			String sql = query.toString();
			
			logger.info(sql);
			
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_READ_ONLY);
			
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
	
	protected Pais loadNext(ResultSet rs) throws SQLException{
		int i = 1;
		
		Pais p = new Pais();
		
		p.setId(rs.getShort(i++));
		p.setNombre(rs.getString(i++));
		p.setIso2(rs.getString(i++));
		p.setIso3(rs.getString(i++));
		p.setTelefonoCode(rs.getString(i++));
		
		return p;
	}
}
