package com.pinguela.retroworld.junit;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import com.pinguela.retroworld.model.Pais;
import com.pinguela.retroworld.service.PaisService;
import com.pinguela.retroworld.service.impl.PaisServiceImpl;

public class PaisServiceTest {
	private PaisService paisService=null;
	
	public PaisServiceTest() {
		paisService = new PaisServiceImpl();
	}
	
	@Test
	public void testFindByAll() throws Exception{
		List<Pais>paises = paisService.findByAll();
		assertEquals(248, paises.size());
	}
	
	@Test
	public void testFindById01()throws Exception{
		Integer id = 64;
		Pais pais = paisService.findById(id);
		assertEquals(id, (short)pais.getId());
	}
	
	@Test
	public void testFindById02()throws Exception{
		Integer id = 299;
		Pais pais = paisService.findById(id);
		assertNull(pais);
	}

}
