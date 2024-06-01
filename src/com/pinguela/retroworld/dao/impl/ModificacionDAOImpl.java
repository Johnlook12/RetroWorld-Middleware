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

import com.pinguela.DataException;
import com.pinguela.retroworld.dao.ModificacionDAO;
import com.pinguela.retroworld.model.Modificacion;
import com.pinguela.retroworld.util.JDBCUtils;

public class ModificacionDAOImpl implements ModificacionDAO{

	private static Logger logger = LogManager.getLogger(ModificacionDAOImpl.class);

	public ModificacionDAOImpl() {

	}


	public void create(Connection conn, List<Modificacion> modificaciones) throws DataException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			StringBuilder query = new StringBuilder(" INSERT INTO MODIFICACION(FECHA, TIPO_MODIFICACION_ID, ANUNCIO_ID, EMPLEADO_ID)");
			query.append("VALUES");
			JDBCUtils.appendMultipleInsertParameters(query, "(?,?,?,?)", modificaciones.size());

			pstmt = conn.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);

			logger.info(pstmt);

			int i =1;
			for(Modificacion m:modificaciones) {
				pstmt.setDate(i++, new java.sql.Date(m.getFecha().getTime()));
				pstmt.setInt(i++, m.getIdTipoModificacion());
				pstmt.setLong(i++, m.getIdAnuncio());
				pstmt.setLong(i++, m.getIdEmpleado());
			}

			int insertedRows = pstmt.executeUpdate();

			if(insertedRows!=1) {
				//throws Exception
			}

			rs = pstmt.getGeneratedKeys();

			Long id = null;
			i=0;
			while(rs.next()) {
				id = rs.getLong(1);
				modificaciones.get(i++).setId(id);
			}


		}catch(SQLException e) {
			logger.error("Modificaciones: "+modificaciones);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}

	}


	public List<Modificacion> findByIdEmpleado(Connection conn, Long id) throws DataException{
		List<Modificacion> resultados = new ArrayList<Modificacion>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			StringBuilder query = new StringBuilder(" SELECT M.ID, M.FECHA, A.TITULO, E.ID, TM.NOMBRE, A.ID, TM.ID");
			query.append(" FROM MODIFICACION M");
			query.append(" INNER JOIN ANUNCIO A ON A.ID = M.ANUNCIO_ID");
			query.append(" INNER JOIN EMPLEADO E ON E.ID = M.EMPLEADO_ID");
			query.append(" INNER JOIN TIPO_MODIFICACION TM ON TM.ID = TIPO_MODIFICACION_ID");
			query.append(" WHERE E.ID = ?");
			query.append(" ORDER BY M.FECHA DESC");

			String sql = query.toString();

			logger.info(sql);

			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pstmt.setLong(i++, id);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				resultados.add(loadNext(rs));
			}

		}catch(SQLException e) {
			logger.error("ID: "+id, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}

		return resultados;
	}


	public List<Modificacion> findByTipoModificacion(Connection conn, Short idTipo) throws DataException{
		List<Modificacion> resultados = new ArrayList<Modificacion>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			StringBuilder query = new StringBuilder(" SELECT M.ID, M.FECHA, A.TITULO, E.ID, TM.NOMBRE, A.ID, TM.ID");
			query.append(" FROM MODIFICACION M");
			query.append(" INNER JOIN ANUNCIO A ON A.ID = M.ANUNCIO_ID");
			query.append(" INNER JOIN EMPLEADO E ON E.ID = M.EMPLEADO_ID");
			query.append(" INNER JOIN TIPO_MODIFICACION TM ON TM.ID = TIPO_MODIFICACION_ID");
			query.append(" WHERE TM.ID = ?");
			query.append(" ORDER BY M.FECHA DESC");

			String sql = query.toString();

			logger.info(sql);

			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pstmt.setInt(i++, idTipo);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				resultados.add(loadNext(rs));
			}


		}catch(SQLException e) {
			logger.error("Tipo ID: "+idTipo, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}


		return resultados;
	}

	@Override
	public List<Modificacion> findByIdAnuncio(Connection conn, Long idAnuncio) throws DataException {
		List<Modificacion> resultados = new ArrayList<Modificacion>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			StringBuilder query = new StringBuilder(" SELECT M.ID, M.FECHA, A.TITULO, E.ID, TM.NOMBRE, A.ID, TM.ID");
			query.append(" FROM MODIFICACION M");
			query.append(" INNER JOIN ANUNCIO A ON A.ID = M.ANUNCIO_ID");
			query.append(" INNER JOIN EMPLEADO E ON E.ID = M.EMPLEADO_ID");
			query.append(" INNER JOIN TIPO_MODIFICACION TM ON TM.ID = TIPO_MODIFICACION_ID");
			query.append(" WHERE A.ID = ?");
			query.append(" ORDER BY M.FECHA DESC");

			String sql = query.toString();

			logger.info(sql);

			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pstmt.setLong(i++, idAnuncio);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				resultados.add(loadNext(rs));
			}


		}catch(SQLException e) {
			logger.error("ID Anuncio: "+idAnuncio, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}


		return resultados;
	}

	protected Modificacion loadNext(ResultSet rs) throws SQLException{
		Modificacion m = new Modificacion();
		int i =1;

		m.setId(rs.getLong(i++));
		m.setFecha(rs.getDate(i++));
		m.setTituloAnuncio(rs.getString(i++));
		m.setIdEmpleado(rs.getLong(i++));
		m.setTipoModificacion(rs.getString(i++));
		m.setIdAnuncio(rs.getLong(i++));
		m.setIdTipoModificacion(rs.getInt(i++));

		return m;
	}
}
