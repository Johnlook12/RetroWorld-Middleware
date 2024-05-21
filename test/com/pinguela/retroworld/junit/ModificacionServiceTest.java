package com.pinguela.retroworld.junit;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.Test;

import com.pinguela.retroworld.model.Modificacion;
import com.pinguela.retroworld.service.ModificacionService;
import com.pinguela.retroworld.service.impl.ModificacionServiceImpl;

public class ModificacionServiceTest {
	private ModificacionService modificacionService=null;
	
	public ModificacionServiceTest() {
		modificacionService = new ModificacionServiceImpl();
	}
	
	@Test
	public void testFindByIdEmpleadoExistente() throws Exception{
		Long idEmpleado = 2l;
		List<Modificacion> modificaciones = modificacionService.findByIdEmpleado(idEmpleado);
		assertNotEquals(0, modificaciones.size());
	}
	
	@Test
	public void testFindByIdEmpleadoInexistente()throws Exception{
		Long idEmpleado = -400l;
		List<Modificacion> modificaciones = modificacionService.findByIdEmpleado(idEmpleado);
		assertEquals(0, modificaciones.size());
	}
	
	@Test
	public void testFindByTipoModificacionExistente()throws Exception{
		Short idTipo=2;
		List<Modificacion> modificaciones= modificacionService.findByTipoModificacion(idTipo);
		assertNotEquals(0, modificaciones.size());
	}
	
	@Test
	public void testFindByTipoModificacionInexistente()throws Exception{
		Short idTipo=-2;
		List<Modificacion> modificaciones= modificacionService.findByTipoModificacion(idTipo);
		assertEquals(0, modificaciones.size());
	}
}
