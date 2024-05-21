package com.pinguela.retroworld.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.dao.DireccionDAO;
import com.pinguela.retroworld.dao.EmpleadoDAO;
import com.pinguela.retroworld.model.Empleado;
import com.pinguela.retroworld.util.JDBCUtils;
import com.pinguela.retroworld.util.SQLUtils;

public class EmpleadoDAOImpl implements EmpleadoDAO{
	
	private static Logger logger = LogManager.getLogger(EmpleadoDAOImpl.class);
	
	private DireccionDAO direccionDAO= null;
	
	public EmpleadoDAOImpl() {
		direccionDAO = new DireccionDAOImpl();
	}
	
	
	public void create(Connection conn, Empleado empleado) 
							throws DataException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder query = new StringBuilder(" INSERT INTO EMPLEADO(NOMBRE, APELLIDO1, APELLIDO2, DNI_NIE, TELEFONO, EMAIL, PASSWORD, FECHA_BAJA, TIPO_EMPLEADO_ID)");
			query.append("VALUES (?,?,?,?,?,?,?,?,?)");
			
			pstmt = conn.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			
			if(logger.isInfoEnabled()) {
				logger.info(query.toString());
			}
			
			int i=1;
			pstmt.setString(i++, empleado.getNombre());
			pstmt.setString(i++, empleado.getApellido1());
			pstmt.setString(i++, empleado.getApellido2());
			pstmt.setString(i++, empleado.getDocumentoIdentidad());
			pstmt.setString(i++, empleado.getTelefono());
			pstmt.setString(i++, empleado.getEmail());
			pstmt.setString(i++, empleado.getPassword());
			JDBCUtils.setNullable(pstmt, i++, empleado.getFechaBaja());
			pstmt.setShort(i++, empleado.getIdTipoEmpleado());
			
			
			
			int insertedRows = pstmt.executeUpdate();
			
			if(insertedRows!=1) {
				//throws Exception
			}
			
			rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				Long id = rs.getLong(1);
				empleado.setId(id);
				empleado.getDireccion().setIdEmpleado(id);
			}
			
			direccionDAO.create(conn, empleado.getDireccion());
			
		} catch(SQLException e) {
			logger.error("empleado: "+empleado,e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
	}
	



	public boolean update(Connection conn, Empleado empleado) throws DataException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(" UPDATE EMPLEADO SET"
										  +" NOMBRE = ?,"
										  +" APELLIDO1 = ?,"
										  +" APELLIDO2 = ?,"
										  +" DNI_NIE = ?,"
										  +" TELEFONO = ?,"
										  +" PASSWORD = ?,"
										  +" EMAIL = ?,"
										  +" TIPO_EMPLEADO_ID = ?"
										  +" WHERE ID = ?");
			
			if(logger.isInfoEnabled()) {
				logger.info(pstmt);
			}
			
			int i = 1;
			pstmt.setString(i++, empleado.getNombre());
			pstmt.setString(i++, empleado.getApellido1());
			JDBCUtils.setNullable(pstmt, i++, empleado.getApellido2());
			pstmt.setString(i++, empleado.getDocumentoIdentidad());
			JDBCUtils.setNullable(pstmt, i++, empleado.getTelefono());
			pstmt.setString(i++, empleado.getPassword());
			pstmt.setString(i++, empleado.getEmail());
			pstmt.setShort(i++, empleado.getIdTipoEmpleado());
			pstmt.setLong(i++, empleado.getId());
			
			int updatedRows = pstmt.executeUpdate();
			
			if(updatedRows != 1) {
				return false;
			}
			
		}catch(SQLException e) {
			logger.error("empleado: "+empleado, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt);
		}
		return true;
	}

	public boolean updatePassword(Connection conn, String password, Long id) throws DataException{
		PreparedStatement pstmt= null;
		try {
			pstmt = conn.prepareStatement(" UPDATE EMPLEADO SET PASSWORD = ? WHERE ID = ?");
			
			int i = 1;
			pstmt.setString(i++, password);
			pstmt.setLong(i++, id);
			
			int updatedRows = pstmt.executeUpdate();
			
			if(updatedRows!=1) {
				return false;
			}
		} catch(SQLException e) {
			logger.error(e);
			throw new DataException(e);
		}
		return true;
	}


	public boolean delete(Connection conn, Long id) throws DataException{
		PreparedStatement pstmt = null;
		try {
			direccionDAO.deleteByEmpleado(conn, id);
			
			pstmt = conn.prepareStatement(" UPDATE EMPLEADO SET FECHA_BAJA = ? WHERE ID = ?");
			if(logger.isInfoEnabled()) {
				logger.info(pstmt);
			}
			int i =1;
			pstmt.setDate(i++, new java.sql.Date(new Date().getTime()));
			pstmt.setLong(i++, id);
			
			int deleteRows = pstmt.executeUpdate();
			
			if(deleteRows !=1) {
				return false;
			}
		}catch(SQLException e) {
			logger.error("id: "+id, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt);
		}
		return true;
	}

	
	
	public Empleado findById(Connection conn, Long id) throws DataException{
		Empleado resultado = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			StringBuilder query = new StringBuilder(" SELECT ID, NOMBRE, APELLIDO1, APELLIDO2, DNI_NIE, TELEFONO, PASSWORD, EMAIL, TIPO_EMPLEADO_ID");
			query.append(" FROM EMPLEADO");
			query.append(" WHERE ID = ?");
			
			String sql = query.toString();
			
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_READ_ONLY);
			
			if(logger.isInfoEnabled()) {
				logger.info(sql);
			}
			
			int i=1;
			
			pstmt.setLong(i++, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultado = loadNext(rs, conn);
			}
			
		}catch(SQLException e) {
			logger.error("id: "+id, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
		
		
		return resultado;
	}

	
	public Empleado findByEmail(Connection conn, String email) throws DataException{
		
		Empleado resultado = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			StringBuilder query = new StringBuilder(" SELECT ID, NOMBRE, APELLIDO1, APELLIDO2, DNI_NIE, TELEFONO, PASSWORD, EMAIL, TIPO_EMPLEADO_ID");
			query.append(" FROM EMPLEADO");
			query.append(" WHERE EMAIL LIKE ?");
			
			String sql = query.toString();
			
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_READ_ONLY);
			
			if(logger.isInfoEnabled()) {
				logger.info(sql);
			}
			
			int i=1;
			
			pstmt.setString(i++, SQLUtils.wrapLike(SQLUtils.wrapLike(email)));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultado = loadNext(rs, conn);
			}
			
		}catch(SQLException e) {
			logger.error("email: "+email, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
		
		return resultado;
	}

	
	public List<Empleado> findByAll(Connection conn) throws DataException{
		List<Empleado> resultados = new ArrayList<Empleado>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			StringBuilder query = new StringBuilder("SELECT ID, NOMBRE, APELLIDO1, APELLIDO2, DNI_NIE, TELEFONO, PASSWORD, EMAIL, TIPO_EMPLEADO_ID");
			query.append(" FROM EMPLEADO");
			query.append(" ORDER BY ID DESC");
			
			String sql = query.toString();
			
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				resultados.add(loadNext(rs, conn));
			}
		}catch (SQLException e) {
			logger.error(e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
		
		return resultados;
	}
	
	protected Empleado loadNext(ResultSet rs, Connection conn) throws SQLException, DataException{
		Empleado e = new Empleado();
		int i =1;
		
		e.setId(rs.getLong(i++));
		e.setNombre(rs.getString(i++));
		e.setApellido1(rs.getString(i++));
		e.setApellido2(rs.getString(i++));
		e.setDocumentoIdentidad(rs.getString(i++));
		e.setTelefono(rs.getString(i++));
		e.setPassword(rs.getString(i++));
		e.setEmail(rs.getString(i++));
		e.setIdTipoEmpleado(rs.getShort(i++));
		
		e.setDireccion(direccionDAO.findByIdEmpleado(conn, e.getId()));
		
		return e;
	}
}
