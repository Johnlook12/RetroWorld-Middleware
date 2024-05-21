package com.pinguela.retroworld.junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import com.pinguela.retroworld.model.Direccion;
import com.pinguela.retroworld.service.DireccionService;
import com.pinguela.retroworld.service.impl.DireccionServiceImpl;

public class DireccionServiceTest {
	
	private DireccionService direccionService= null;
	
	public DireccionServiceTest() {
		direccionService = new DireccionServiceImpl();
	}
	
	@Test
	public void testFindById01() throws Exception{
		Long id = 9l;
		Direccion d = direccionService.findById(id);
		assertEquals(id, d.getId());
	}
	
	@Test
	public void testFindById02()throws Exception{
		Long id = -5l;
		Direccion d = direccionService.findById(id);
		assertNull(d);
	}
	
	@Test
	public void testFindByAll()throws Exception{
		List<Direccion> direcciones = direccionService.findByAll();
		assertEquals(32, direcciones.size());
	}
	@Test
	public void testFindByIdUsuario01()throws Exception{
		Long id = 2l;
		List<Direccion> direcciones = direccionService.findByIdUsuario(id);
		for(Direccion d:direcciones) {
			assertEquals(id, d.getIdUsuario());
		}
	}
	
	@Test
	public void testFindByIdUsuario02()throws Exception{
		Long id=-2l;
		List<Direccion>direcciones = direccionService.findByIdUsuario(id);
		assertTrue(direcciones.isEmpty());
	}
	
	@Test
	public void testCreate()throws Exception{
		Direccion d = new Direccion();
		d.setCodigoPostal(27400l);
		d.setDirVia("13");
		d.setIdUsuario(3l);
		d.setLetra("C");
		d.setTipoVia("Rúa");
		d.setNombreVia("Central");
		d.setPiso(3);
		
		direccionService.create(d);
		assertNotNull(d.getId());
	}
	
		@Test 
		public void testUpdate()throws Exception{
		Long id = 19l;
		Direccion d = direccionService.findById(id);
		d.setPiso(2);
		d.setLetra("A");
		boolean direccionUpdated = direccionService.update(d);
		assertTrue(direccionUpdated);
	}
		
		@Test
		public void testDelete()throws Exception{
			Long id=2l;
			boolean direccionDeleted = direccionService.delete(id);
			assertTrue(direccionDeleted);
		}
}
