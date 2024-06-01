package com.pinguela.retroworld.util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.pinguela.DataException;
import com.pinguela.retroworld.config.ConfigurationParametersManager;
public class JDBCUtils {
	private static Logger logger = LogManager.getLogger(JDBCUtils.class);
	private static final String DB_URL_PNAME = "db.url";
	private static final String USER_PNAME = "db.user";
	private static final String PASS_PNAME = "db.password";
	private static final String DRIVER_PNAME = "db.driver";
	
	private static ComboPooledDataSource cpds = new ComboPooledDataSource();

	static {
		try {
			cpds.setDriverClass(ConfigurationParametersManager.getValue(DRIVER_PNAME));
			cpds.setJdbcUrl(ConfigurationParametersManager.getValue(DB_URL_PNAME));
			cpds.setUser(ConfigurationParametersManager.getValue(USER_PNAME));
			cpds.setPassword(ConfigurationParametersManager.getValue(PASS_PNAME));
		} catch (PropertyVetoException e) {
			logger.fatal(e);
		}
	}

	public JDBCUtils() {
	}


	public static Connection getConnection() throws SQLException {
//		try {
//			Class.forName(DRIVER);
//		} catch(ClassNotFoundException cnfe) {
//			logger.fatal("Imposible cargar driver JDBC "+DRIVER);
//		}
		Connection conn = cpds.getConnection();
		return conn;
	}

	public static final StringBuilder appendMultipleInsertParameters(StringBuilder query, String pattern, int size) {

		for(int i = 0;i<size-1;i++) {
			query.append(pattern).append(",");

		}
		query.append(pattern);

		return query;
	}

	public static final Long getNullableLong(ResultSet rs, int pos) throws SQLException{
		Long value = rs.getLong(pos);
		return rs.wasNull()?null:value;
	}

	public static final void setNullable(PreparedStatement ps, int i, String value)	
			throws SQLException {
		if (value==null) {			
			ps.setNull(i, Types.VARCHAR);
		} else {
			ps.setString(i, value);
		}
	}

	public static final void setNullable(PreparedStatement ps, int i, Date value)	
			throws SQLException {
		if (value==null) {			
			ps.setNull(i, Types.DATE);
		} else {
			ps.setDate(i, new java.sql.Date(value.getTime()));
		}
	}

	public static final void setNullable(PreparedStatement ps, int i, Long value)	
			throws SQLException {
		if (value==null) {			
			ps.setNull(i, Types.INTEGER);
		} else {
			ps.setLong(i, value);
		}
	}

	public static final void setNullable(PreparedStatement ps, int i, Integer value)	
			throws SQLException {
		if (value==null) {			
			ps.setNull(i, Types.INTEGER);
		} else {
			ps.setLong(i, value);
		}
	}

	public static final void setNullable(PreparedStatement ps, int i, Boolean value)	
			throws SQLException {
		if (value==null) {			
			ps.setNull(i, Types.BOOLEAN);
		} else {
			ps.setBoolean(i, value);
		}
	}

	public static final void close(ResultSet rs) {
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error(e);
			} 
		}		
	}

	public static final void close(Statement st) {
		if (st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}

	}

	public static final void close(Connection con) {
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}

	}

	public static final void close(Connection con, boolean commitOrRollback) 
			throws DataException {
		if (con!=null) {
			try {
				if (commitOrRollback) {
					con.commit();
				} else {
					con.rollback();
				}
				con.close();
			} catch (SQLException e) {
				logger.error(e);
				throw new DataException(e);
			}
		}
	}


	public static final void close(Statement s, ResultSet rs) {
		if (s!=null) {
			try {
				s.close();
			} catch (SQLException e)  {
				logger.error(e);
			}
		}
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e)  {
				logger.error(e);
			}
		}
	}

	public static final int getTotalRows(ResultSet resultSet) throws SQLException {
		int totalRows = 0;
		if(resultSet.last()) {
			totalRows = resultSet.getRow();
		}
		resultSet.beforeFirst();
		return totalRows;
	}
}
