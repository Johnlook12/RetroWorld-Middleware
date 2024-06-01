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
import com.pinguela.retroworld.dao.LocalidadDAO;
import com.pinguela.retroworld.model.Localidad;
import com.pinguela.retroworld.util.JDBCUtils;

public class LocalidadDAOImpl implements LocalidadDAO{
	
	private static Logger logger = LogManager.getLogger(LocalidadDAOImpl.class);
	
	public LocalidadDAOImpl() {
		
	}

	
	public List<Localidad> findByIdProvincia(Connection conn, Short id) throws DataException{
		List<Localidad> resultados = new ArrayList<Localidad>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			StringBuilder query = new StringBuilder(" SELECT L.ID, L.NOMBRE, L.PROVINCIA_ID, P.NOMBRE");
			query.append(" FROM LOCALIDAD L");
			query.append(" INNER JOIN PROVINCIA P ON P.ID = L.PROVINCIA_ID");
			query.append(" WHERE L.PROVINCIA_ID = ?");
			query.append(" ORDER BY L.ID ASC");
			
			String sql = query.toString();
			
			logger.info(sql);
			
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			
			pstmt.setShort(i++, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultados.add(loadNext(rs));
			}
			
		
		}catch(SQLException e) {
			logger.error("ID : "+id, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
		
		
		
		return resultados;
	}

	
	public Localidad findById(Connection conn, Long id) throws DataException{
		Localidad resultado = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			StringBuilder query = new StringBuilder(" SELECT L.ID, L.NOMBRE, L.PROVINCIA_ID, P.NOMBRE");
			query.append(" FROM LOCALIDAD L");
			query.append(" INNER JOIN PROVINCIA P ON P.ID = L.PROVINCIA_ID");
			query.append(" WHERE L.ID = ?");
			
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
			logger.error("ID: "+id, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
		
		
		
		return resultado;
	}
	
	protected Localidad loadNext(ResultSet rs) throws SQLException{
		Localidad l = new Localidad();
		int i =1;
		
		l.setCodigoPostal(rs.getLong(i++));
		l.setNombre(rs.getString(i++));
		l.setIdProvincia(rs.getShort(i++));
		l.setNombreProvincia(rs.getString(i++));
		
		return l;
	}
	
	

}
