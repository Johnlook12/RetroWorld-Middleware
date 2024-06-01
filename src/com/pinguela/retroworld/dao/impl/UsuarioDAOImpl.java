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

import com.pinguela.DataException;
import com.pinguela.retroworld.dao.DireccionDAO;
import com.pinguela.retroworld.dao.UsuarioDAO;
import com.pinguela.retroworld.model.Usuario;
import com.pinguela.retroworld.util.JDBCUtils;

public class UsuarioDAOImpl implements UsuarioDAO{
	
	private DireccionDAO direccionDAO = null;
	private static Logger logger = LogManager.getLogger(UsuarioDAOImpl.class);
	
	public UsuarioDAOImpl() {
		direccionDAO = new DireccionDAOImpl();
	}
	
	
	public boolean update(Connection conn, Usuario u) throws DataException{
		PreparedStatement pstmt = null;
		try {
			
			StringBuilder query = new StringBuilder(" UPDATE USUARIO SET");
			query.append(" NOMBRE = ?,");
			query.append(" APELLIDO1 = ?,");
			query.append(" APELLIDO2 = ?,");
			query.append(" DNI_NIE = ?,");
			query.append(" TELEFONO = ?,");
			query.append(" EMAIL = ?,");
			query.append(" PASSWORD = ?");
			query.append(" WHERE ID = ?");
			
			logger.info(query.toString());
			
			pstmt = conn.prepareStatement(query.toString());
			int i = 1;
			pstmt.setString(i++, u.getNombre());
			pstmt.setString(i++, u.getApellido1());
			pstmt.setString(i++, u.getApellido2());
			pstmt.setString(i++, u.getDocumentoIdentidad());
			pstmt.setString(i++, u.getTelefono());
			pstmt.setString(i++, u.getEmail());
			pstmt.setString(i++, u.getPassword());
			pstmt.setLong(i++, u.getId());
			
			int updatedRows = pstmt.executeUpdate();
			
			if(updatedRows != 1) {
				//throws Exception
			}
			
		}catch(SQLException e) {
			logger.info("Usuario: "+u, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt);
		}
		return true;
	}
	
	public boolean updatePassword(Connection conn, String password, Long id) throws DataException{
		PreparedStatement pstmt= null;
		try {
			pstmt = conn.prepareStatement(" UPDATE USUARIO SET PASSWORD = ? WHERE ID = ?");
			
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

	public void create(Connection conn, Usuario u) throws DataException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			StringBuilder query = new StringBuilder(" INSERT INTO USUARIO(NOMBRE, APELLIDO1, APELLIDO2, DNI_NIE, TELEFONO, EMAIL, PASSWORD)");
			query.append("VALUES (?,?,?,?,?,?,?)");
			
			logger.info(query.toString());
			
			pstmt = conn.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			int i=1;
			pstmt.setString(i++, u.getNombre());
			pstmt.setString(i++, u.getApellido1());
			JDBCUtils.setNullable(pstmt, i++, u.getApellido2());
			pstmt.setString(i++, u.getDocumentoIdentidad());
			pstmt.setString(i++, u.getTelefono());
			pstmt.setString(i++, u.getEmail());
			pstmt.setString(i++, u.getPassword());
			
			int insertedRows = pstmt.executeUpdate();
			
			if(insertedRows!=1) {
				//throws Exception
			}
			
			rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				Long id = rs.getLong(1);
				u.setId(id);
			}
			
		}catch(SQLException e) {
			logger.error("Usuario: "+u, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
		
	}
	
	public boolean softDelete(Connection conn, Long id) throws DataException{
		PreparedStatement pstmt = null;
		try {
			direccionDAO.deleteByUsuario(conn, id);
			pstmt = conn.prepareStatement(" UPDATE USUARIO SET FECHA_BAJA = ? WHERE ID = ?");
			
			logger.info(pstmt);
			
			int i = 1;
			pstmt.setDate(i++, new java.sql.Date(new Date().getTime()));
			pstmt.setLong(i++, id);
			
			int deleteRows = pstmt.executeUpdate();
			
			if(deleteRows != 1) {
				return false;
			}
		}catch(SQLException e) {
			logger.error("ID : "+id, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt);
		}
		return true;
	}
	
	public Usuario findById(Connection conn, Long id) throws DataException{
		Usuario resultado = null;
		
		try {
		
			StringBuilder query = new StringBuilder(" SELECT ID, NOMBRE, APELLIDO1, APELLIDO2, DNI_NIE, TELEFONO, PASSWORD, EMAIL, NICKNAME");
			query.append(" FROM USUARIO");
			query.append(" WHERE ID = ?");
			
			String sql = query.toString();
			
			logger.info(sql);
			
			PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int i=1;
			
			pstmt.setLong(i++, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultado = loadNext(rs);
			}
			
		}catch(SQLException e) {
			logger.error("ID: "+id, e);
			throw new DataException(e);
		}
		
		
		return resultado;
	}

	
	public Usuario findByEmail(Connection conn, String email) throws DataException{
		
		Usuario resultado = null;
		
		try {
			
			StringBuilder query = new StringBuilder(" SELECT ID, NOMBRE, APELLIDO1, APELLIDO2, DNI_NIE, TELEFONO, PASSWORD, EMAIL, NICKNAME");
			query.append(" FROM USUARIO");
			query.append(" WHERE EMAIL = ?");
			
			String sql = query.toString();
			
			logger.info(sql);
			
			PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int i=1;
			
			pstmt.setString(i++,email);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultado = loadNext(rs);
			}
			
		}catch(SQLException e) {
			logger.error("Email: "+email, e);
			throw new DataException(e);
		}
		
		return resultado;
	}

	
	public List<Usuario> findByAll(Connection conn) throws DataException{
		List<Usuario> resultados = new ArrayList<Usuario>();
		
		try {
			
			StringBuilder query = new StringBuilder("SELECT ID, NOMBRE, APELLIDO1, APELLIDO2, DNI_NIE, TELEFONO, PASSWORD, EMAIL, NICKNAME");
			query.append(" FROM USUARIO");
			query.append(" ORDER BY ID ASC");
			
			String sql = query.toString();
			
			logger.info(sql);
			
			PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rs= pstmt.executeQuery();
			
			while(rs.next()) {
				resultados.add(loadNext(rs));
			}
					
					
			
		}catch (SQLException e) {
			logger.error(e);
			throw new DataException(e);
		}
		
		return resultados;
	}
	
	protected Usuario loadNext(ResultSet rs) throws SQLException{
		Usuario u = new Usuario();
		int i =1;
		
		u.setId(rs.getLong(i++));
		u.setNombre(rs.getString(i++));
		u.setApellido1(rs.getString(i++));
		u.setApellido2(rs.getString(i++));
		u.setDocumentoIdentidad(rs.getString(i++));
		u.setTelefono(rs.getString(i++));
		u.setPassword(rs.getString(i++));
		u.setEmail(rs.getString(i++));
		u.setNickName(rs.getString(i++));
		
		return u;
	}
}
