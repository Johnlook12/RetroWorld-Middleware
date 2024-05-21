package com.pinguela.retroworld.junit;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import com.pinguela.retroworld.model.Provincia;
import com.pinguela.retroworld.service.ProvinciaService;
import com.pinguela.retroworld.service.impl.ProvinciaServiceImpl;

public class ProvinciaServiceTest {
	private ProvinciaService provinciaService = null;

	public ProvinciaServiceTest() {
		provinciaService = new ProvinciaServiceImpl();
	}
	
	@Test
	public void testFindById01() throws Exception{
		Short id = 1;
		Provincia provincia = provinciaService.findById(id);
		assertEquals(id, provincia.getId());
	}
	
	@Test
	public void testFindById02()throws Exception{
		Short id = -1;
		Provincia provincia = provinciaService.findById(id);
		assertNull(provincia);
	}
	
	@Test
	public void testFindByIdPais01()throws Exception{
		Short idPais = 64;
		List<Provincia>provincias=provinciaService.findByIdPais(idPais);
		for(Provincia p:provincias) {
			assertEquals(idPais, p.getIdPais());
		}
	}
	
	@Test
	public void testFindByIdPais02()throws Exception{
		Short idPais=299;
		List<Provincia>provincias=provinciaService.findByIdPais(idPais);
		assertTrue(provincias.isEmpty());
	}
}
