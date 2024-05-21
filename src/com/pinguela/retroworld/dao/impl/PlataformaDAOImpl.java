package com.pinguela.retroworld.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.dao.PlataformaDAO;
import com.pinguela.retroworld.model.Plataforma;
import com.pinguela.retroworld.util.JDBCUtils;

public class PlataformaDAOImpl implements PlataformaDAO{
	private static Logger logger = LogManager.getLogger(PlataformaDAOImpl.class);
	
	public PlataformaDAOImpl() {
		
	}
	
	public List<Plataforma> findByAll(Connection conn) throws DataException{
		List<Plataforma> resultados = new ArrayList<Plataforma>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT ID, NOMBRE, FECHA_LANZAMIENTO FROM PLATAFORMA ORDER BY ID ASC", 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
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

	
	public List<Plataforma> findByVideojuego(Connection conn, Long idVideojuego) throws DataException{
		List<Plataforma> resultados = new ArrayList<Plataforma>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder query = new StringBuilder(" SELECT P.ID, P.NOMBRE, P.FECHA_LANZAMIENTO");
			query.append(" FROM PLATAFORMA P");
			query.append(" INNER JOIN VIDEOJUEGO_PLATAFORMA VP ON VP.PLATAFORMA_ID = P.ID");
			query.append(" INNER JOIN VIDEOJUEGO V ON V.ID = VP.VIDEOJUEGO_ID");
			query.append(" WHERE V.ID = ?");	
			query.append(" ORDER BY P.ID ASC");
			
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
	
	protected Plataforma loadNext(ResultSet rs) throws SQLException{
		Plataforma p = new Plataforma();
		int i=1;
		
		p.setId(rs.getInt(i++));
		p.setNombre(rs.getString(i++));
		p.setFechaLanzamiento(rs.getDate(i++));
		
		return p;
	}

}
