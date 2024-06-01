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
import com.pinguela.retroworld.dao.DireccionDAO;
import com.pinguela.retroworld.model.Direccion;
import com.pinguela.retroworld.util.JDBCUtils;

public class DireccionDAOImpl implements DireccionDAO{
	
	private static Logger logger = LogManager.getLogger(DireccionDAOImpl.class);
	
	public DireccionDAOImpl() {
		
	}
	
	
	public void create(Connection conn, Direccion d) 
									throws DataException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			StringBuilder query = new StringBuilder(" INSERT INTO DIRECCION(TIPO_VIA, NOMBRE_VIA, DIR_VIA, LOCALIDAD_ID, PISO, LETRA, USUARIO_ID, EMPLEADO_ID)");
			query.append("VALUES (?,?,?,?,?,?,?,?)");
			
			pstmt = conn.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			int i =1;
			
			logger.info(query.toString());
			
			pstmt.setString(i++, d.getTipoVia());
			pstmt.setString(i++, d.getNombreVia());
			pstmt.setString(i++, d.getDirVia());
			pstmt.setLong(i++, d.getCodigoPostal()); //El id de la localidad es lo mismo que el código postal
			pstmt.setInt(i++, d.getPiso());
			pstmt.setString(i++, d.getLetra());
			JDBCUtils.setNullable(pstmt, i++, d.getIdUsuario());
			JDBCUtils.setNullable(pstmt, i++, d.getIdEmpleado());
			
			int insertedRows = pstmt.executeUpdate();
			
			if(insertedRows != 1) {
				//throws Exception
			}
			
			rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				Long id = rs.getLong(1);
				d.setId(id);
			}
			
			
		} catch (SQLException e) {
			logger.error("direccion: "+d, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
		
	}
	
	
	public boolean update(Connection conn, Direccion d) throws DataException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(" UPDATE DIRECCION SET"
											+" TIPO_VIA = ?,"
											+" NOMBRE_VIA = ?,"
											+" DIR_VIA = ?,"
											+" LOCALIDAD_ID = ?,"
											+" PISO = ?,"
											+" LETRA = ?"
											+"WHERE ID = ?");
			int i = 1;
			
			logger.info(pstmt);
			
			pstmt.setString(i++, d.getTipoVia());
			pstmt.setString(i++, d.getNombreVia());
			pstmt.setString(i++, d.getDirVia());
			pstmt.setLong(i++, d.getCodigoPostal());
			JDBCUtils.setNullable(pstmt, i++, d.getPiso());
			JDBCUtils.setNullable(pstmt, i++, d.getLetra());
			pstmt.setLong(i++, d.getId());
			
			int updatedRows = pstmt.executeUpdate();
			
			if(updatedRows != 1) {
				return false;
			}
			
		} catch(SQLException e) {
			logger.error("direccion: "+d, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt);			
		}
		return true;
	}

	
	public boolean delete(Connection conn, Long id) throws DataException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(" DELETE FROM DIRECCION WHERE ID = ?");
			
			int i = 1;
			
			logger.info(pstmt);
			
			pstmt.setLong(i++, id);
			
			int deletedRows = pstmt.executeUpdate();
			
			if(deletedRows != 1) {
				return false;
			}
			
		} catch(SQLException e) {
			logger.error("id: "+id, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt);
		}
		return true;
	}
	
	
	public boolean deleteByEmpleado(Connection conn, Long idEmpleado) throws DataException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(" DELETE FROM DIRECCION WHERE EMPLEADO_ID = ?");
			int i = 1;
			
			logger.info(pstmt);
			
			pstmt.setLong(i++, idEmpleado);
			
			int deleteRows = pstmt.executeUpdate();
			
			if(deleteRows==0) {
				return false;
			}
		}catch(SQLException e) {
			logger.error("Empleado ID : "+idEmpleado, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt);
		}
		return true;
	}
	
	public boolean deleteByUsuario(Connection conn, Long idUsuario) throws DataException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(" DELETE FROM DIRECCION WHERE USUARIO_ID = ?");
			
			logger.info(pstmt);
			
			int i = 1;
			pstmt.setLong(i++, idUsuario);
			
			int deleteRows = pstmt.executeUpdate();
			
			if(deleteRows==0) {
				return false;
			}
		}catch(SQLException e) {
			logger.error("Usuario ID: "+idUsuario, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt);
		}
		
		return false;
	}

	
	public List<Direccion> findByIdUsuario(Connection conn, Long id) throws DataException{
		List<Direccion> resultados = new ArrayList<Direccion>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder query = new StringBuilder(" SELECT D.ID, D.TIPO_VIA, D.NOMBRE_VIA, D.DIR_VIA, D.LOCALIDAD_ID, D.PISO, D.LETRA, L.NOMBRE, D.EMPLEADO_ID, D.USUARIO_ID");
			query.append(" FROM DIRECCION D");
			query.append(" INNER JOIN USUARIO U ON U.ID=D.USUARIO_ID");
			query.append(" INNER JOIN LOCALIDAD L ON D.LOCALIDAD_ID = L.ID");
			query.append(" WHERE U.ID = ?");
			query.append(" ORDER BY D.ID ASC");
			String sql = query.toString();
			
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_READ_ONLY);
			
			logger.info(sql);
			
			int i =1;
			
			pstmt.setLong(i++, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {  
				resultados.add(loadNext(rs));
			}
			
		}catch(SQLException e) {
			logger.error("id: "+id, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
		
		
		return resultados;
	}

	
	public Direccion findByIdEmpleado(Connection conn, Long id) throws DataException{
		Direccion resultado = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder query = new StringBuilder(" SELECT D.ID, D.TIPO_VIA, D.NOMBRE_VIA, D.DIR_VIA, D.LOCALIDAD_ID, D.PISO, D.LETRA, L.NOMBRE, D.EMPLEADO_ID, D.USUARIO_ID");
			query.append(" FROM DIRECCION D");
			query.append(" INNER JOIN LOCALIDAD L ON D.LOCALIDAD_ID = L.ID");
			query.append(" INNER JOIN EMPLEADO E ON E.ID = D.EMPLEADO_ID");
			query.append(" WHERE E.ID = ?");
			
			String sql = query.toString();
			
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_READ_ONLY);
			
			int i =1;
			
			pstmt.setLong(i++, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				resultado = loadNext(rs);
			}
			
		}catch(SQLException e) {
			logger.error("id: "+id, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
		
		return resultado;
	}

	
	public Direccion findById(Connection conn, Long id) throws DataException{
		Direccion resultado = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder query = new StringBuilder(" SELECT D.ID, D.TIPO_VIA, D.NOMBRE_VIA, D.DIR_VIA, D.LOCALIDAD_ID, D.PISO, D.LETRA, L.NOMBRE, D.EMPLEADO_ID, D.USUARIO_ID");
			query.append(" FROM DIRECCION D");
			query.append(" INNER JOIN LOCALIDAD L ON D.LOCALIDAD_ID = L.ID");
			query.append(" WHERE D.ID = ?");
			
			String sql = query.toString();
			
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_READ_ONLY);
			
			logger.info(sql);
			
			int i =1;
			
			pstmt.setLong(i++, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultado = loadNext(rs);
			}
			
			
		}catch(SQLException e) {
			logger.error("id: "+id, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
		return resultado;
	}

	
	public List<Direccion> findByAll(Connection conn) throws DataException{
		List<Direccion> resultados = new ArrayList<Direccion>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder query = new StringBuilder(" SELECT D.ID, D.TIPO_VIA, D.NOMBRE_VIA, D.DIR_VIA, D.LOCALIDAD_ID, D.PISO, D.LETRA, L.NOMBRE, D.EMPLEADO_ID, D.USUARIO_ID");
			query.append(" FROM DIRECCION D");
			query.append(" INNER JOIN LOCALIDAD L ON D.LOCALIDAD_ID = L.ID");
			query.append(" ORDER BY D.ID ASC");
			
			
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
	
	protected Direccion loadNext(ResultSet rs) throws SQLException {
		Direccion d = new Direccion();
		
		int i = 1;
		
		d.setId(rs.getLong(i++));
		d.setTipoVia(rs.getString(i++));
		d.setNombreVia(rs.getString(i++));
		d.setDirVia(rs.getString(i++));
		d.setCodigoPostal(rs.getLong(i++));
		d.setPiso(rs.getInt(i++));
		d.setLetra(rs.getString(i++));
		d.setNombreLocalidad(rs.getString(i++));
		d.setIdEmpleado(JDBCUtils.getNullableLong(rs, i++));
		d.setIdUsuario(JDBCUtils.getNullableLong(rs, i++));
		
		return d;	
	}
}
