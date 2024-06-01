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
import com.pinguela.retroworld.dao.GeneroDAO;
import com.pinguela.retroworld.dao.IdiomaDAO;
import com.pinguela.retroworld.dao.PlataformaDAO;
import com.pinguela.retroworld.dao.VideojuegoDAO;
import com.pinguela.retroworld.model.Genero;
import com.pinguela.retroworld.model.Idioma;
import com.pinguela.retroworld.model.Plataforma;
import com.pinguela.retroworld.model.Results;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.service.VideojuegoCriteria;
import com.pinguela.retroworld.util.JDBCUtils;
import com.pinguela.retroworld.util.SQLUtils;

public class VideojuegoDAOImpl implements VideojuegoDAO{

	private static Logger logger = LogManager.getLogger(VideojuegoDAOImpl.class);
	private IdiomaDAO idiomaDAO = null;
	private PlataformaDAO plataformaDAO = null;
	private GeneroDAO generoDAO = null;
	
	public VideojuegoDAOImpl() {
		idiomaDAO = new IdiomaDAOImpl();
		plataformaDAO = new PlataformaDAOImpl();
		generoDAO = new GeneroDAOImpl();
	}

	public void create(Connection conn, Videojuego v) throws DataException{

		try {

			StringBuilder query = new StringBuilder("INSERT INTO VIDEOJUEGO(NOMBRE, FECHA_LANZAMIENTO, DESCRIPCION, DESARROLLADORA_ID)");
			query.append(" VALUES(?,?,?,?)");

			PreparedStatement pstmt = conn.prepareStatement(query.toString(), 
					Statement.RETURN_GENERATED_KEYS);

			logger.info(query.toString());

			int j = 1;

			pstmt.setString(j++, v.getNombre());
			JDBCUtils.setNullable(pstmt, j++, new java.util.Date(v.getFechaLanzamiento().getTime()));
			JDBCUtils.setNullable(pstmt, j++, v.getDescripcion());
			pstmt.setLong(j++, v.getIdDesarrolladora());

			int insertedRows = pstmt.executeUpdate();

			if(insertedRows!=1) {
				//trows Exception
			}

			ResultSet rs = pstmt.getGeneratedKeys();

			if(rs.next()) {
				Long id = rs.getLong(1);
				v.setId(id);
			}
			
			List<Integer> idsIdiomas = new ArrayList<Integer>();
			List<Integer> idsGeneros = new ArrayList<Integer>();
			List<Integer> idsPlataformas = new ArrayList<Integer>();
			
			if(v.getIdiomas()!=null) {
				for(Idioma i:v.getIdiomas()) {
					idsIdiomas.add(i.getId());
				}
				asignarIdioma(conn, v.getId(), idsIdiomas);
			}
			if(v.getGeneros()!=null) {
				for(Genero g:v.getGeneros()) {
					idsGeneros.add(g.getId());
				}
				asignarGenero(conn, v.getId(), idsGeneros);
			}
			if(v.getPlataformas()!=null) {
				for(Plataforma p:v.getPlataformas()) {
					idsPlataformas.add(p.getId());
				}
				asignarPlataforma(conn, v.getId(), idsPlataformas);
			}

		} catch(SQLException e) {
			logger.error("Videojuego: "+v, e);
			throw new DataException();
		}

	}


	public boolean update(Connection conn, Videojuego v) throws DataException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(" UPDATE VIDEOJUEGO SET"
					+" NOMBRE = ?,"
					+" DESCRIPCION = ?,"
					+" FECHA_LANZAMIENTO = ?,"
					+" DESARROLLADORA_ID = ?"
					+" WHERE ID = ?");
			logger.info(pstmt);

			int i = 1;
			pstmt.setString(i++, v.getNombre());
			JDBCUtils.setNullable(pstmt, i++, v.getDescripcion());
			pstmt.setDate(i++, new java.sql.Date(v.getFechaLanzamiento().getTime()));
			pstmt.setInt(i++, v.getIdDesarrolladora());
			pstmt.setLong(i++, v.getId());

			int updatedRows = pstmt.executeUpdate();

			if(updatedRows != 1) {
				return false;
			}

		} catch(SQLException e) {
			logger.error("Videojuego: "+v, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt);
		}
		return true;
	}


	public Results<Videojuego> findBy(Connection conn, VideojuegoCriteria criteria, int pos, int pageSize) throws DataException{
		Results<Videojuego> resultados = new Results<Videojuego>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			List<String> condiciones = new ArrayList<String>();
			StringBuilder query = new StringBuilder(" SELECT V.ID, V.NOMBRE, V.DESCRIPCION, V.FECHA_LANZAMIENTO, D.NOMBRE, V.DESARROLLADORA_ID");
			query.append(" FROM VIDEOJUEGO V");
			query.append(" LEFT JOIN DESARROLLADORA D ON D.ID = V.DESARROLLADORA_ID");
			query.append(" LEFT JOIN VIDEOJUEGO_IDIOMA VI ON VI.VIDEOJUEGO_ID = V.ID");
			query.append(" LEFT JOIN IDIOMA I ON VI.IDIOMA_ID = I.ID");
			query.append(" LEFT JOIN VIDEOJUEGO_GENERO VG ON VG.VIDEOJUEGO_ID = V.ID");
			query.append(" LEFT JOIN GENERO G ON VG.GENERO_ID = G.ID");
			query.append(" LEFT JOIN VIDEOJUEGO_PLATAFORMA VP ON VP.VIDEOJUEGO_ID = V.ID");
			query.append(" LEFT JOIN PLATAFORMA P ON VP.PLATAFORMA_ID = P.ID");
			
			if(criteria.getId()!=null) {
				condiciones.add(" V.ID = ?");
			} else {

				if(criteria.getNombre()!=null) {
					condiciones.add(" V.NOMBRE LIKE ?");
				}

				if(criteria.getFechaLanzamientoDesde()!=null) {
					condiciones.add(" V.FECHA_LANZAMIENTO >= ?");
				}

				if(criteria.getFechaLanzamientoHasta()!=null) {
					condiciones.add(" V.FECHA_LANZAMIENTO<= ?");
				}

				if(criteria.getNombreDesarrolladora()!=null) {
					condiciones.add(" D.NOMBRE LIKE ?");
				}

				if(criteria.getNombreIdioma()!=null) {
					condiciones.add(" I.NOMBRE LIKE ?");
				}

				if(criteria.getNombrePlataforma()!=null) {
					condiciones.add(" P.NOMBRE LIKE ?");
				}

				if(criteria.getNombreGenero()!=null) {
					condiciones.add(" G.NOMBRE LIKE ?");
				}

				if(criteria.getIdIdioma()!=null) {
					condiciones.add(" I.ID = ?");
				}

				if(criteria.getIdDesarrolladora()!=null) {
					condiciones.add(" D.ID = ?");
				}

				if(criteria.getIdGenero()!=null) {
					condiciones.add(" G.ID = ?");
				}

				if(criteria.getIdPlataforma()!=null) {
					condiciones.add(" P.ID = ?");
				}
			}


			if(!condiciones.isEmpty()) {
				query.append(" WHERE");
				query.append(String.join(" AND", condiciones));
			}
			
			query.append(" GROUP BY V.ID ");
			query.append(" ORDER BY ").append(criteria.getOrderBy()).append(criteria.getAscDesc()?" ASC":" DESC");

			String sql = query.toString();

			logger.info(sql);

			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_READ_ONLY);

			int i = 1;

			if(criteria.getId()!=null) {
				pstmt.setLong(i++, criteria.getId());
			}

			if(criteria.getNombre()!=null) {
				pstmt.setString(i++, SQLUtils.wrapLike(criteria.getNombre()) );
			}

			if(criteria.getFechaLanzamientoDesde()!=null) {
				pstmt.setDate(i++, new java.sql.Date(criteria.getFechaLanzamientoDesde().getTime()));
			}

			if(criteria.getFechaLanzamientoHasta()!=null) {
				pstmt.setDate(i++, new java.sql.Date(criteria.getFechaLanzamientoHasta().getTime()));
			}

			if(criteria.getNombreDesarrolladora()!=null) {
				pstmt.setString(i++, SQLUtils.wrapLike(criteria.getNombreDesarrolladora()));
			}

			if(criteria.getNombreIdioma()!=null) {
				pstmt.setString(i++,SQLUtils.wrapLike(criteria.getNombreIdioma()));
			}

			if(criteria.getNombrePlataforma()!=null) {
				pstmt.setString(i++, SQLUtils.wrapLike(criteria.getNombrePlataforma()));
			}

			if(criteria.getNombreGenero()!=null) {
				pstmt.setString(i++, SQLUtils.wrapLike(criteria.getNombreGenero()));
			}

			if(criteria.getIdIdioma()!=null) {
				pstmt.setInt(i++, criteria.getIdIdioma());
			}

			if(criteria.getIdDesarrolladora()!=null) {
				pstmt.setInt(i++, criteria.getIdDesarrolladora());
			}

			if(criteria.getIdGenero()!=null) {
				pstmt.setInt(i++, criteria.getIdGenero());
			}

			if(criteria.getIdPlataforma()!=null) {
				pstmt.setInt(i++, criteria.getIdPlataforma());
			}

			rs = pstmt.executeQuery();
			
			int count = 0;
			
			if(pos>=1 && rs.absolute(pos)) {
				do {
					resultados.getPage().add(loadNext(rs, conn));
					count++;
				} while(count<pageSize && rs.next());
			}
			
			resultados.setTotal(JDBCUtils.getTotalRows(rs));
		} catch (SQLException e) {
			logger.error("Criterios: "+criteria, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
		return resultados;
	}

	public Videojuego findById(Connection conn, Long id) throws DataException{
		Videojuego resultado = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder query = new StringBuilder(" SELECT DISTINCT V.ID, V.NOMBRE, V.DESCRIPCION, V.FECHA_LANZAMIENTO, D.NOMBRE, V.DESARROLLADORA_ID");
			query.append(" FROM VIDEOJUEGO V");
			query.append(" INNER JOIN DESARROLLADORA D ON D.ID = V.DESARROLLADORA_ID");
			query.append(" INNER JOIN VIDEOJUEGO_IDIOMA VI ON VI.VIDEOJUEGO_ID = V.ID");
			query.append(" INNER JOIN IDIOMA I ON VI.IDIOMA_ID = I.ID");
			query.append(" INNER JOIN VIDEOJUEGO_GENERO VG ON VG.VIDEOJUEGO_ID = V.ID");
			query.append(" INNER JOIN GENERO G ON VG.GENERO_ID = G.ID");
			query.append(" INNER JOIN VIDEOJUEGO_PLATAFORMA VP ON VP.VIDEOJUEGO_ID = V.ID");
			query.append(" INNER JOIN PLATAFORMA P ON VP.PLATAFORMA_ID = P.ID");
			query.append(" WHERE V.ID = ?");
			
			pstmt = conn.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			 
			int i = 1;
			pstmt.setLong(i++, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				resultado = loadNext(rs, conn);
			}
		} catch(SQLException e){
			logger.error("ID: "+id, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt, rs);
		}
		return resultado;
	}


	
	public void asignarIdioma(Connection conn, Long idVideojuego, List<Integer>idsIdiomas) throws DataException{
		PreparedStatement pstmt = null;
		List<Idioma> idiomas = idiomaDAO.findByVideojuego(conn, idVideojuego);
		
		if(idiomas.isEmpty()) {
			
		} else {
			try {
				pstmt = conn.prepareStatement("DELETE FROM VIDEOJUEGO_IDIOMA WHERE VIDEOJUEGO_ID = ?");
				
				int i = 1;
				
				pstmt.setLong(i++, idVideojuego);
				
				pstmt.executeUpdate();
			} catch(SQLException e) {
				logger.error("ID Videojuego: "+idVideojuego+"ID's Idiomas: "+idsIdiomas, e);
				throw new DataException(e);
			} finally {
				JDBCUtils.close(pstmt);
			}
		}
		
		try {
			StringBuilder query = new StringBuilder(" INSERT INTO VIDEOJUEGO_IDIOMA(VIDEOJUEGO_ID, IDIOMA_ID)");
			query.append(" VALUES");
			JDBCUtils.appendMultipleInsertParameters(query, "(?,?)", idsIdiomas.size());
			
			pstmt = conn.prepareStatement(query.toString());
			
			int i = 1;
			
			for(Integer id:idsIdiomas) {
				pstmt.setLong(i++, idVideojuego);
				pstmt.setLong(i++, id);
			}
			
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			logger.error("ID Videojuego: "+idVideojuego+"ID's Idiomas: "+idsIdiomas, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt);
		}
		
	}


	
	public void asignarPlataforma(Connection conn, Long idVideojuego, List<Integer> idsPlataformas) throws DataException{
		PreparedStatement pstmt = null;
		List<Plataforma> plataformas = plataformaDAO.findByVideojuego(conn, idVideojuego);
		
		if(plataformas.isEmpty()) {
			
		} else {
			try {
				pstmt = conn.prepareStatement("DELETE FROM VIDEOJUEGO_PLATAFORMA WHERE VIDEOJUEGO_ID = ?");
				
				int i = 1;
				
				pstmt.setLong(i++, idVideojuego);
				
				pstmt.executeUpdate();
			} catch(SQLException e) {
				logger.error("ID Videojuego: "+idVideojuego+"ID's Plataformas: "+idsPlataformas, e);
				throw new DataException(e);
			} finally {
				JDBCUtils.close(pstmt);
			}
		}
		
		try {
			
			StringBuilder query = new StringBuilder(" INSERT INTO VIDEOJUEGO_PLATAFORMA(VIDEOJUEGO_ID, PLATAFORMA_ID)");
			query.append("VALUES");
			JDBCUtils.appendMultipleInsertParameters(query, "(?,?)", idsPlataformas.size());
			
			pstmt = conn.prepareStatement(query.toString());
			
			int i = 1;
			
			for(Integer id:idsPlataformas) {
				pstmt.setLong(i++, idVideojuego);
				pstmt.setLong(i++, id);
			}
			
			
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			logger.error("ID Videojuego: "+idVideojuego+"ID's Plataformas: "+idsPlataformas, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt);
		}
		
	}


	
	public void asignarGenero(Connection conn, Long idVideojuego, List<Integer> idsGeneros) throws DataException{
		PreparedStatement pstmt = null;
		List<Genero> generos = generoDAO.findByVideojuego(conn, idVideojuego);
		
		if(generos.isEmpty()) {
			
		} else {
			try {
				pstmt = conn.prepareStatement("DELETE FROM VIDEOJUEGO_GENERO WHERE VIDEOJUEGO_ID = ?");
				
				int i = 1;
				
				pstmt.setLong(i++, idVideojuego);
				
				pstmt.executeUpdate();
			} catch(SQLException e) {
				logger.error("ID Videojuego: "+idVideojuego+"ID's Géneros: "+idsGeneros, e);
				throw new DataException(e);
			} finally {
				JDBCUtils.close(pstmt);
			}
		}
		
		try {
			
			StringBuilder query = new StringBuilder(" INSERT INTO VIDEOJUEGO_GENERO(VIDEOJUEGO_ID, GENERO_ID)");
			query.append("VALUES");
			JDBCUtils.appendMultipleInsertParameters(query, "(?,?)", idsGeneros.size());
			pstmt = conn.prepareStatement(query.toString());
			
			int i = 1;
			
			for(Integer id:idsGeneros) {
				pstmt.setLong(i++, idVideojuego);
				pstmt.setLong(i++, id);
			}
			
			pstmt.executeUpdate();
		} catch(SQLException e) {
			logger.error("ID Videojuego: "+idVideojuego+"ID's Generos: "+idsGeneros, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pstmt);
		}
		
	}
	
	protected Videojuego loadNext (ResultSet rs, Connection conn) throws SQLException, DataException{
		int i;
		i=1;
		Videojuego v=new Videojuego();
		v.setId(rs.getLong(i++));
		v.setNombre(rs.getString(i++));
		v.setDescripcion(rs.getString(i++));
		v.setFechaLanzamiento(rs.getDate(i++));
		v.setNombreDesarrolladora(rs.getString(i++));
		v.setIdDesarrolladora(rs.getInt(i++));
		
		
		List<Idioma> idiomas = idiomaDAO.findByVideojuego(conn,v.getId());
		List<Genero> generos = generoDAO.findByVideojuego(conn, v.getId());
		List<Plataforma> plataformas = plataformaDAO.findByVideojuego(conn, v.getId());
		v.setIdiomas(idiomas);
		v.setGeneros(generos);
		v.setPlataformas(plataformas);

		return v;
	}

}
