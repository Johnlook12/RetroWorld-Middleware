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
import com.pinguela.retroworld.dao.DesarrolladoraDAO;
import com.pinguela.retroworld.model.Desarrolladora;

public class DesarrolladoraDAOImpl implements DesarrolladoraDAO{
	
	private static Logger logger = LogManager.getLogger(DesarrolladoraDAOImpl.class);
	
	public DesarrolladoraDAOImpl() {
		
	}
	
	@Override
	public List<Desarrolladora> findByAll(Connection conn) throws DataException {
		List<Desarrolladora> resultados = new ArrayList<Desarrolladora>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT ID, NOMBRE, FECHA_FUNDACION, PAIS_ID FROM DESARROLLADORA ORDER BY ID ASC", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultados.add(loadNext(rs));
			}
		}catch(SQLException e) {
			logger.error(e);
			throw new DataException();
		}
		return resultados;
	}

	protected Desarrolladora loadNext(ResultSet rs) throws SQLException{
		Desarrolladora d = new Desarrolladora();
		int i = 1;
		d.setId(rs.getInt(i++));
		d.setNombre(rs.getString(i++));
		d.setFechaFundación(rs.getDate(i++));
		d.setIdPais(rs.getShort(i++));
		
		return d;
	}
}
