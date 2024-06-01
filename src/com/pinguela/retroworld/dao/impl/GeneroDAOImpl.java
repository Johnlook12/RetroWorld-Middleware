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
import com.pinguela.retroworld.dao.GeneroDAO;
import com.pinguela.retroworld.model.Genero;
import com.pinguela.retroworld.util.JDBCUtils;

public class GeneroDAOImpl implements GeneroDAO{
	private static Logger logger = LogManager.getLogger(GeneroDAOImpl.class);
	
	public GeneroDAOImpl() {
		
	}
	
	public List<Genero> findByAll(Connection conn) throws DataException{
		List<Genero> resultados = new ArrayList<Genero>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT ID, NOMBRE FROM GENERO ORDER BY ID ASC", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
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

	
	public List<Genero> findByVideojuego(Connection conn, Long idVideojuego) throws DataException{
		List<Genero> resultados = new ArrayList<Genero>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder query = new StringBuilder(" SELECT G.ID, G.NOMBRE");
			query.append(" FROM GENERO G");
			query.append(" INNER JOIN VIDEOJUEGO_GENERO VG ON VG.GENERO_ID = G.ID");
			query.append(" INNER JOIN VIDEOJUEGO V ON V.ID = VG.VIDEOJUEGO_ID");
			query.append(" WHERE V.ID = ?");	
			query.append(" ORDER BY G.ID ASC");
			
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
	
	protected Genero loadNext(ResultSet rs) throws SQLException{
		Genero g = new Genero();
		int i = 1;
		
		g.setId(rs.getInt(i++));
		g.setNombre(rs.getString(i++));
		
		return g;
	}
	
}
