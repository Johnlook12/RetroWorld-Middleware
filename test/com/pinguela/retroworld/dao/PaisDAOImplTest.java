package com.pinguela.retroworld.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import com.pinguela.retroworld.dao.impl.PaisDAOImpl;
import com.pinguela.retroworld.model.Pais;
import com.pinguela.retroworld.util.JDBCUtils;

public class PaisDAOImplTest {
	
	private PaisDAO paisDAO = null;
	private Connection conn = null;
	
	public PaisDAOImplTest(){
		paisDAO = new PaisDAOImpl();
	}

	@Test
	public void testFindById() throws Exception{
		conn = JDBCUtils.getConnection();
		Pais pais = new Pais();
		pais = paisDAO.findById(conn, 5);
		assertEquals((short)5, pais.getId());
	}

	@Test
	public void testFindByAll() throws Exception{
		conn = JDBCUtils.getConnection();
		List<Pais> paises = paisDAO.findByAll(conn);
		assertEquals(248, paises.size());
	}

}
