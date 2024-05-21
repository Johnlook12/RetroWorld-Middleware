package com.pinguela.retroworld.junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.Test;

import com.pinguela.retroworld.model.Usuario;
import com.pinguela.retroworld.service.UsuarioService;
import com.pinguela.retroworld.service.impl.UsuarioServiceImpl;

public class UsuarioServiceTest {
	private UsuarioService usuarioService=null;
	
	public UsuarioServiceTest() {
		usuarioService=new UsuarioServiceImpl();
	}
	
	@Test
	public void testFindById01() throws Exception{
		Long id = 2l;
		Usuario u = usuarioService.findById(id);
		assertEquals(id, u.getId());
	}
	
	@Test
	public void testFindById02()throws Exception{
		Long id = -2l;
		Usuario u = usuarioService.findById(id);
		assertNull(u);
	}
	
	@Test
	public void testFindByEmailExistente()throws Exception{
		String email = "luisa.gonzalez@email.com";
		Usuario u = usuarioService.findByEmail(email);
		assertEquals(email, u.getEmail());
	}
	
	@Test
	public void testFindByEmailInexistente()throws Exception{
		String email = "alksndioz@email.com";
		Usuario u = usuarioService.findByEmail(email);
		assertNull(u);
	}
	
	@Test
	public void testFindByAll()throws Exception{
		List<Usuario> usuarios = usuarioService.findByAll();
		assertNotEquals(0, usuarios.size());
	}
	
	@Test
	public void testRegistrar()throws Exception{
		Usuario u = new Usuario();
		u.setEmail("johnnrodriguez0112@gmail.com");
		u.setApellido1("Rodríguez");
		u.setNombre("Johnn");
		u.setPassword("abc12345.@");
		u.setTelefono("1234568786");
		u.setDocumentoIdentidad("12312123");
		
		usuarioService.registrar(u);
		assertNotNull(u.getId());
	}
	
	@Test
	public void testAutentificacionValida() throws Exception{
		String email="johnnrodriguez0112@gmail.com";
		String password="abc12345.@";
		Usuario user = usuarioService.autentificar(email, password);
		assertNotNull(user);
	}
	
	@Test
	public void testAutentificacionEmailInvalido() throws Exception{
		String email="michaeljackson@gmail.com";
		String password="abc12345.@";
		Usuario user = usuarioService.autentificar(email, password);
		assertNull(user);
	}
	
	@Test
	public void testAutentificacionPasswordInvalido() throws Exception{
		String email="johnnrodriguez0112@gmail.com";
		String password="198827hsna1";
		Usuario user = usuarioService.autentificar("ja01@gmail.com", password);
		assertNull(user);
	}
	
	@Test
	public void testUpdate() throws Exception{
		Long id = 3l;
		Usuario u = usuarioService.findById(id);
		u.setApellido1("Montoya");
		u.setTelefono("652784536");
		boolean usuarioUpdated = usuarioService.update(u);
		assertTrue(usuarioUpdated);
	}
	
	@Test
	public void testUpdatePassword()throws Exception{
		Long id = 3l;
		String password = "abc123456.@#";
		boolean passwordUpdated = usuarioService.updatePassword(password, id);
		assertTrue(passwordUpdated);
	}
	
	@Test
	public void testSoftDelete()throws Exception{
		Long id=10l;
		boolean usuarioDeleted = usuarioService.delete(id);
		assertTrue(usuarioDeleted);
	}

}
