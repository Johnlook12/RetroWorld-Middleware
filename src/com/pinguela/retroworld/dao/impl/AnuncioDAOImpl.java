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
import com.pinguela.retroworld.dao.AnuncioDAO;
import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.model.EstadoAnuncio;
import com.pinguela.retroworld.model.Results;
import com.pinguela.retroworld.service.AnuncioCriteria;
import com.pinguela.retroworld.util.JDBCUtils;
import com.pinguela.retroworld.util.SQLUtils;

public class AnuncioDAOImpl implements AnuncioDAO {

	private static Logger logger = LogManager.getLogger(AnuncioDAOImpl.class);

	public AnuncioDAOImpl() {

	}


	public boolean update(Connection conn, Anuncio a) throws DataException{
		PreparedStatement pstmt = null;
		try {

			pstmt = conn.prepareStatement("UPDATE ANUNCIO SET"
					+" TITULO = ?,"
					+" DESCRIPCION = ?,"
					+" FECHA_INICIO = ?,"
					+" FECHA_FIN = ?,"
					+" PRECIO = ?,"
					+" VIDEOJUEGO_ID = ?,"
					+" USUARIO_ID = ?,"
					+" EMPLEADO_ID = ?,"
					+" ESTADO_VIDEOJUEGO_ID = ?,"
					+" ESTADO_ANUNCIO_ID = ?"
					+" WHERE ID = ?");
			logger.info(pstmt);

			int i = 1;
			pstmt.setString(i++, a.getTitulo());
			pstmt.setString(i++, a.getDescripcion());
			pstmt.setDate(i++, new java.sql.Date(a.getFechaInicio().getTime()));
			JDBCUtils.setNullable(pstmt, i++, a.getFechaFin());
			pstmt.setDouble(i++, a.getPrecio());
			pstmt.setLong(i++, a.getIdVideojuego());
			JDBCUtils.setNullable(pstmt, i++, a.getIdUsuario());
			JDBCUtils.setNullable(pstmt, i++, a.getIdEmpleado());
			pstmt.setInt(i++, a.getEstadoVideojuego());
			pstmt.setInt(i++, a.getIdEstadoAnuncio());
			pstmt.setLong(i++, a.getId());

			int updatedRows = pstmt.executeUpdate();

			if(updatedRows!=1) {
				return false;
			}

		}catch(SQLException e) {
			logger.error("anuncio: "+a, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt);
		}
		return true;
	}


	public boolean softDelete(Connection conn, Long idAnuncio) throws DataException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("UPDATE ANUNCIO SET FECHA_FIN = ?, ESTADO_ANUNCIO_ID = ? WHERE ID = ?");

			logger.info(pstmt);

			int i =1;
			pstmt.setDate(i++, new java.sql.Date(new Date().getTime()));
			pstmt.setInt(i++, EstadoAnuncio.FINALIZADO);
			pstmt.setLong(i++, idAnuncio);

			int deleteRows = pstmt.executeUpdate();

			if(deleteRows==0) {
				return false;
			}

		}catch(SQLException e) {
			logger.error("id : "+idAnuncio, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt);
		}
		return true;
	}

	public void create(Connection conn, Anuncio a) throws DataException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = conn.prepareStatement(" INSERT INTO ANUNCIO(TITULO, DESCRIPCION, FECHA_INICIO, FECHA_FIN, PRECIO, VIDEOJUEGO_ID, USUARIO_ID, EMPLEADO_ID, ESTADO_VIDEOJUEGO_ID, ESTADO_ANUNCIO_ID)"
					+" \nVALUES (?,?,?,?,?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			int i= 1;
			
			logger.info(pstmt);

			pstmt.setString(i++, a.getTitulo());
			pstmt.setString(i++, a.getDescripcion());
			pstmt.setDate(i++, new java.sql.Date(a.getFechaInicio().getTime()));
			
			JDBCUtils.setNullable(pstmt, i++, a.getFechaFin());
			pstmt.setDouble(i++, a.getPrecio());
			pstmt.setLong(i++, a.getIdVideojuego());
			JDBCUtils.setNullable(pstmt, i++, a.getIdUsuario());
			JDBCUtils.setNullable(pstmt, i++, a.getIdEmpleado());
			pstmt.setInt(i++, a.getEstadoVideojuego());
			pstmt.setInt(i++, a.getIdEstadoAnuncio());

			int insertedRows = pstmt.executeUpdate();

			if(insertedRows!=1) {
				// throw new ...Exception
			}

			rs = pstmt.getGeneratedKeys();

			if(rs.next()) {
				Long id = rs.getLong(1);
				a.setId(id);
			} else {
				// throw new ...Exception
			}

		}catch(SQLException e) {
			logger.error("Anuncio : "+a, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
	}

	public Results<Anuncio> findBy(Connection conn, AnuncioCriteria criteria, int pos, int pageSize) throws DataException{
		Results<Anuncio> resultados = new Results<Anuncio>();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {

			StringBuilder query = new StringBuilder(" SELECT A.ID, A.TITULO, A.DESCRIPCION, A.FECHA_INICIO, A.FECHA_FIN, A.PRECIO, A.VIDEOJUEGO_ID, ");
			query.append("A.USUARIO_ID, A.EMPLEADO_ID, A.ESTADO_VIDEOJUEGO_ID, V.NOMBRE, EA.NOMBRE, EA.ID"); 
			query.append(" FROM ANUNCIO A");
			query.append(" INNER JOIN ESTADO_ANUNCIO EA ON EA.ID = A.ESTADO_ANUNCIO_ID");
			query.append(" INNER JOIN VIDEOJUEGO V ON A.VIDEOJUEGO_ID = V.ID");
			query.append(" LEFT JOIN VIDEOJUEGO_GENERO VG ON VG.VIDEOJUEGO_ID = V.ID");
			query.append(" LEFT JOIN GENERO G ON G.ID = VG.GENERO_ID");
			query.append(" LEFT JOIN VIDEOJUEGO_IDIOMA VI ON VI.VIDEOJUEGO_ID = V.ID");
			query.append(" LEFT JOIN IDIOMA I ON I.ID = VI.IDIOMA_ID");
			query.append(" LEFT JOIN VIDEOJUEGO_PLATAFORMA VP ON VP.VIDEOJUEGO_ID = V.ID");
			query.append(" LEFT JOIN PLATAFORMA P ON P.ID = VP.PLATAFORMA_ID");

			List<String> condiciones = new ArrayList<String>();
			

			if(criteria.getIdAnuncio()!=null) {
				condiciones.add(" A.ID = ?");
			} else {
				if(criteria.getIdVideojuego()!=null) {
					condiciones.add(" A.VIDEOJUEGO_ID = ? ");
				}

				if (criteria.getFechaInicio()!=null) {
					condiciones.add(" A.FECHA_INICIO = ? ");
				}

				if (criteria.getFechaFin() != null) {
					condiciones.add(" A.FECHA_FIN = ?");
				}

				if (criteria.getPrecioDesde()!= null) {
					condiciones.add(" A.PRECIO>= ?");
				}

				if(criteria.getPrecioHasta()!=null){
					condiciones.add(" A.PRECIO<= ?");
				}

				if (criteria.getIdEstado()!=null) {
					condiciones.add(" A.ESTADO_VIDEOJUEGO_ID = ?");
				}

				if(criteria.getNombre()!=null) {
					condiciones.add(" A.TITULO LIKE ?");
				}
				
				if(criteria.getDescripcion()!=null) {
					condiciones.add(" A.DESCRIPCION LIKE ?");
				}

				if(criteria.getIdEmpleado()!=null) {
					condiciones.add( " A.EMPLEADO_ID = ?" );
				}

				if(criteria.getIdUsuario()!=null) {
					condiciones.add(" USUARIO_ID = ?");
				}

				if(criteria.getNombreVideojuego()!=null) {
					condiciones.add(" V.NOMBRE LIKE ?");
				}
				if(criteria.getIdEstadoAnuncio()!=null) {
					condiciones.add(" EA.ID = ?");
				}
				if(criteria.getIdIdiomaVideojuego()!=null) {
					condiciones.add(" I.ID = ?");
				}
				if(criteria.getIdGeneroVideojuego()!=null) {
					condiciones.add(" G.ID = ?");
				}
				if(criteria.getIdPlataformaVideojuego()!=null) {
					condiciones.add(" P.ID = ?");
				}
			}

			if(!condiciones.isEmpty()) {
				query.append(" WHERE ");
				query.append(String.join(" AND ", condiciones));
			}
			
			query.append(" GROUP BY A.ID");
			query.append(" ORDER BY ").append(criteria.getOrderBy()).append(criteria.getAscDesc()?" ASC": " DESC");

			String sql = query.toString();
			
			logger.info(sql);

			preparedStatement = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;

			if (criteria.getIdAnuncio()!=null) {
				preparedStatement.setLong(i++, criteria.getIdAnuncio());
			} else {
				if(criteria.getIdVideojuego()!=null) {
					preparedStatement.setLong(i++, criteria.getIdVideojuego());
				}

				if(criteria.getFechaInicio()!=null) {
					preparedStatement.setDate(i++, new java.sql.Date(criteria.getFechaInicio().getTime()));
				}

				if(criteria.getFechaFin()!=null) {
					preparedStatement.setDate(i++, new java.sql.Date(criteria.getFechaFin().getTime()));
				}

				if(criteria.getPrecioDesde()!=null) {
					preparedStatement.setDouble(i++, criteria.getPrecioDesde());
				}

				if(criteria.getPrecioHasta()!=null) {
					preparedStatement.setDouble(i++, criteria.getPrecioHasta());
				}

				if(criteria.getNombre()!=null) {
					preparedStatement.setString(i++, SQLUtils.wrapLike(criteria.getNombre()));
				}
				
				if(criteria.getDescripcion()!=null) {
					preparedStatement.setString(i++, SQLUtils.wrapLike(criteria.getDescripcion()));
				}

				if(criteria.getIdEstado()!=null) {
					preparedStatement.setShort(i++, criteria.getIdEstado());
				}

				if(criteria.getIdEmpleado()!=null) {
					preparedStatement.setLong(i++, criteria.getIdEmpleado());
				}

				if(criteria.getIdUsuario()!=null) {
					preparedStatement.setLong(i++, criteria.getIdUsuario());
				}

				if(criteria.getNombreVideojuego()!=null) {
					preparedStatement.setString(i++, SQLUtils.wrapLike(criteria.getNombreVideojuego()));
				}

				if(criteria.getIdEstadoAnuncio()!=null) {
					preparedStatement.setInt(i++, criteria.getIdEstadoAnuncio());
				}
				
				if(criteria.getIdIdiomaVideojuego()!=null) {
					preparedStatement.setInt(i++, criteria.getIdIdiomaVideojuego());
				}
				
				if(criteria.getIdGeneroVideojuego()!=null) {
					preparedStatement.setInt(i++, criteria.getIdGeneroVideojuego());
				}
				
				if(criteria.getIdPlataformaVideojuego()!=null) {
					preparedStatement.setInt(i++, criteria.getIdPlataformaVideojuego());
				}
			}

			rs = preparedStatement.executeQuery();

			int count = 0;
			
			if(pos>=1 && rs.absolute(pos)) {
				do {
					resultados.getPage().add(loadNext(rs));
					count++;
				} while(count<pageSize && rs.next());
			}
			
			resultados.setTotal(JDBCUtils.getTotalRows(rs));


		} catch(SQLException e) {
			logger.error("Criterios : "+criteria, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(preparedStatement, rs);
		}
		return resultados;
	}
	
	public Anuncio findById(Connection conn, Long id) throws DataException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Anuncio resultado = null;
		try {
			StringBuilder query = new StringBuilder(" SELECT A.ID, A.TITULO, A.DESCRIPCION, A.FECHA_INICIO, A.FECHA_FIN, A.PRECIO, A.VIDEOJUEGO_ID, ");
			query.append("A.USUARIO_ID, A.EMPLEADO_ID, A.ESTADO_VIDEOJUEGO_ID, V.NOMBRE, EA.NOMBRE, EA.ID"); 
			query.append(" FROM ANUNCIO A");
			query.append(" INNER JOIN ESTADO_ANUNCIO EA ON EA.ID = A.ESTADO_ANUNCIO_ID");
			query.append(" INNER JOIN VIDEOJUEGO V ON A.VIDEOJUEGO_ID = V.ID");
			query.append(" WHERE A.ID = ?");
			
			pstmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pstmt.setLong(i++, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				resultado = loadNext(rs);
			}
		} catch(SQLException e) {
			logger.error("ID Anuncio: "+id, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
		return resultado;
	}

	protected Anuncio loadNext(ResultSet rs) throws SQLException{
		int i = 1;

		Anuncio a = new Anuncio();
		a.setId(rs.getLong(i++));;
		a.setTitulo(rs.getString(i++));
		a.setDescripcion(rs.getString(i++));
		a.setFechaInicio(rs.getDate(i++));
		a.setFechaFin(rs.getDate(i++));
		a.setPrecio(rs.getDouble(i++));
		a.setIdVideojuego(rs.getLong(i++));
		a.setIdUsuario(JDBCUtils.getNullableLong(rs, i++));
		a.setIdEmpleado(JDBCUtils.getNullableLong(rs, i++));
		a.setEstadoVideojuego(rs.getInt(i++));
		a.setNombreVideojuego(rs.getString(i++));
		a.setEstadoAnuncio(rs.getString(i++));
		a.setIdEstadoAnuncio(rs.getInt(i++));
		return a;

	}
}


