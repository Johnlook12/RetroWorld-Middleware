package com.pinguela.retroworld.junit;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import com.pinguela.retroworld.model.Localidad;
import com.pinguela.retroworld.service.LocalidadService;
import com.pinguela.retroworld.service.impl.LocalidadServiceImpl;

public class LocalidadServiceTest {
	private LocalidadService localidadService=null;
	
	public LocalidadServiceTest() {
		localidadService = new LocalidadServiceImpl();
	}
	
	@Test
	public void testFindById01() throws Exception{
		Long id = 27400l;
		Localidad l = localidadService.findById(id);
		assertEquals(id, l.getCodigoPostal());
	}
	
	@Test
	public void testFindById02()throws Exception{
		Long id = 3l;
		Localidad l = localidadService.findById(id);
		assertNull(l);
	}
	
	@Test
	public void testFindByIdProvincia01()throws Exception{
		Short idProvincia = 27;
		List<Localidad>localidades=localidadService.findByIdProvincia(idProvincia);
		for(Localidad l:localidades) {
			assertEquals(idProvincia, l.getIdProvincia());
		}
	}
	
	@Test
	public void testFindByIdProvincia02()throws Exception{
		Short idProvincia = 4000;
		List<Localidad>localidades=localidadService.findByIdProvincia(idProvincia);
		assertTrue(localidades.isEmpty());
	}

}
