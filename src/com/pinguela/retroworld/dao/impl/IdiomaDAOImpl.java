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
import com.pinguela.retroworld.dao.IdiomaDAO;
import com.pinguela.retroworld.model.Idioma;
import com.pinguela.retroworld.util.JDBCUtils;

public class IdiomaDAOImpl implements IdiomaDAO{
	private static Logger logger = LogManager.getLogger(IdiomaDAOImpl.class);
	
	public IdiomaDAOImpl() {
		
	}
	
	public List<Idioma> findByAll(Connection conn) throws DataException{
		List<Idioma> resultados = new ArrayList<Idioma>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT ID, NOMBRE FROM IDIOMA ORDER BY NOMBRE ASC", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
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


	public List<Idioma> findByVideojuego(Connection conn, Long idVideojuego) throws DataException{
		List<Idioma> resultados = new ArrayList<Idioma>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder query = new StringBuilder(" SELECT I.ID, I.NOMBRE");
			query.append(" FROM IDIOMA I");
			query.append(" INNER JOIN VIDEOJUEGO_IDIOMA VI ON VI.IDIOMA_ID = I.ID");
			query.append(" INNER JOIN VIDEOJUEGO V ON V.ID = VI.VIDEOJUEGO_ID");
			query.append(" WHERE V.ID = ?");	
			query.append(" ORDER BY I.ID ASC");
			
			pstmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int i = 1;
			pstmt.setLong(i++, idVideojuego);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultados.add(loadNext(rs));
			}
			
		} catch(SQLException e) {
			logger.error("ID Videojuego"+idVideojuego, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
		return resultados;
	}
	
	protected Idioma loadNext(ResultSet rs) throws SQLException{
		Idioma i = new Idioma();
		int j = 1;
		i.setId(rs.getInt(j++));
		i.setNombre(rs.getString(j++));
		return i;
	}

}
