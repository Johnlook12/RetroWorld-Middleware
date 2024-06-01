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
import com.pinguela.retroworld.dao.ProvinciaDAO;
import com.pinguela.retroworld.model.Provincia;
import com.pinguela.retroworld.util.JDBCUtils;

public class ProvinciaDAOImpl implements ProvinciaDAO{
	
	private static Logger logger = LogManager.getLogger(ProvinciaDAOImpl.class);
	
	public ProvinciaDAOImpl() {
		
	}


	public Provincia findById(Connection conn, Short id) throws DataException{
		Provincia resultado = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			StringBuilder query = new StringBuilder(" SELECT P.ID, P.NOMBRE, P.PAIS_ID, C.NOMBRE");
			
			query.append(" FROM PROVINCIA P");
			query.append(" INNER JOIN PAIS C ON C.ID = P.PAIS_ID");
			query.append(" WHERE P.ID = ?");
			
			String sql = query.toString();
			
			logger.info(sql);
			
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_READ_ONLY);
			int  i = 1;
			
			pstmt.setLong(i++, id);
			
			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) {
				resultado=loadNext(rs);
			}
			
			
		} catch(SQLException e) {
			logger.error("ID : "+id, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
		return resultado;
	}

	
	public List<Provincia> findByIdPais(Connection conn, Short id) throws DataException{
		List<Provincia> resultados = new ArrayList<Provincia>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			StringBuilder query = new StringBuilder(" SELECT P.ID, P.NOMBRE, P.PAIS_ID, C.NOMBRE");
			
			query.append(" FROM PROVINCIA P");
			query.append(" INNER JOIN PAIS C ON C.ID = P.PAIS_ID");
			query.append(" WHERE P.PAIS_ID = ?");
			query.append(" ORDER BY P.ID ASC");
			
			String sql = query.toString();
			
			logger.info(sql);
			
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_READ_ONLY);
			
			int i =1;
			
			pstmt.setLong(i++, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultados.add(loadNext(rs));
			}
			
			
		} catch(SQLException e) {
			logger.error("ID: "+id, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
		
		
		
		return resultados;
	}
	
	protected Provincia loadNext(ResultSet rs) throws SQLException{
		Provincia p = new Provincia();
		int i = 1;
		p.setId(rs.getShort(i++));
		p.setNombre(rs.getString(i++));
		p.setIdPais(rs.getShort(i++));
		p.setNombrePais(rs.getString(i++));
		return p;
	}
	
	
	

}
