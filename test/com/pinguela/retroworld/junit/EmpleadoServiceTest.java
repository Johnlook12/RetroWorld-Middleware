package com.pinguela.retroworld.junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.Test;

import com.pinguela.retroworld.model.Direccion;
import com.pinguela.retroworld.model.Empleado;
import com.pinguela.retroworld.service.EmpleadoService;
import com.pinguela.retroworld.service.impl.EmpleadoServiceImpl;

public class EmpleadoServiceTest {
	private EmpleadoService empleadoService=null;
	
	public EmpleadoServiceTest() {
		empleadoService = new EmpleadoServiceImpl();
	}
	
	@Test
	public void testFindById01() throws Exception{
		Long id = 5l;
		Empleado e = empleadoService.findById(id);
		assertEquals(id, e.getId());
	}
	
	@Test
	public void testFindById02()throws Exception{
		Long id = -5l;
		Empleado e = empleadoService.findById(id);
		assertNull(e);
	}
	
	@Test
	public void testFindByEmailExistente()throws Exception{
		String email = "pedro.gomez@email.com";
		Empleado e = empleadoService.findByEmail(email);
		assertEquals(email, e.getEmail());
	}
	
	@Test
	public void testFindByEmailInexistente()throws Exception{
		String email = "333.123gaslz@email.com";
		Empleado e = empleadoService.findByEmail(email);
		assertNull(e);
	}
	
	@Test
	public void testFindByAll()throws Exception{
		List<Empleado> empleados = empleadoService.findByAll();
		assertNotEquals(0, empleados.size());
	}
	
	@Test
	public void testRegistrar()throws Exception{
		Empleado e = new Empleado();
		Direccion d = new Direccion();
		d.setNombreVia("Central");
		d.setTipoVia("Rua");
		d.setCodigoPostal(27400l);
		d.setLetra("C");
		d.setPiso(2);
		d.setDirVia("124");
		
		e.setDireccion(d);
		e.setNombre("Roberto");
		e.setApellido1("Jimenez");
		e.setDocumentoIdentidad("00000000");
		e.setEmail("ja01@gmail.com");
		e.setTelefono("9128382");
		e.setPassword("abc123.");
		empleadoService.registrar(e);
		assertNotNull(e.getId());
	}
	
	@Test
	public void testAutentificacionValida() throws Exception{
		String email="ja01@gmail.com";
		String password="abc123.";
		Empleado empleado = empleadoService.autentificar(email, password);
		assertNotNull(empleado);
	}
	
	@Test
	public void testAutentificacionEmailInvalido() throws Exception{
		String email="1233a123101@mail.com";
		String password="abc123.";
		Empleado empleado = empleadoService.autentificar(email, password);
		assertNull(empleado);
	}
	
	@Test
	public void testAutentificacionPasswordInvalido() throws Exception{
		String email="ja01@gmail.com";
		String password="129919su";
		Empleado empleado = empleadoService.autentificar("ja01@gmail.com", password);
		assertNull(empleado);
	}
	
	@Test
	public void testUpdate() throws Exception{
		Long id = 1l;
		Empleado e = empleadoService.findById(id);
		e.setNombre("Pedro");
		boolean empleadoUpdated = empleadoService.update(e);
		assertTrue(empleadoUpdated);
	}
	
	@Test
	public void testUpdatePassword()throws Exception{
		Long id = 8l;
		String password = "password789";
		boolean passwordUpdated = empleadoService.updatePassword(password, id);
		assertTrue(passwordUpdated);
	}
	
	@Test
	public void testSoftDelete()throws Exception{
		Long id=11l;
		boolean empleadoDeleted = empleadoService.delete(id);
		assertTrue(empleadoDeleted);
	}
	
}
