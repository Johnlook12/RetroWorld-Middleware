package com.pinguela.retroworld.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Usuario;
import com.pinguela.retroworld.service.impl.UsuarioServiceImpl;

public class UsuarioServiceTest {
	
	UsuarioService usuarioService = null;
	private static Logger logger = LogManager.getLogger(UsuarioServiceTest.class);
	
	public UsuarioServiceTest() {
		usuarioService = new UsuarioServiceImpl();
	}
	
	public void testRegistrar() throws Exception{
		logger.traceEntry("Testing registrar...");
		Usuario u = new Usuario();
		logger.traceEntry("Testing registrar...");
		u.setEmail("johnnrodriguez0112@gmail.com");
		u.setApellido1("Rodríguez");
		u.setNombre("Johnn");
		u.setPassword("abc12345.@");
		u.setTelefono("1234568786");
		u.setDocumentoIdentidad("12312123");
		u.setId(11l);
		
		usuarioService.registrar(u);
		logger.info("usuario con id "+u.getId()+" creado con exito");
	}
	
	public void testAutentificacion() throws Exception{
		logger.traceEntry("Testing autentificación usuario y password...");
		Usuario user = usuarioService.autentificar("johnnrodriguez0112@gmail.com", "abc12345.@");
		if(user!=null) {
			logger.info("Autentificación correcta!!!!");
			logger.info(user);
		} else {
			logger.info("fallo en la autentificación con usuario y password");
		}
	}
	
	public void testEmailInexistente() throws Exception{
		logger.traceEntry("Testing autentificación de email no registrado");
		Usuario user = usuarioService.autentificar("michaeljackson23@gmail.com", "abc123.");
		if(user!=null) {
			logger.info("fallo en el método de autentificación");
		} else {
			logger.info("Autentificación de email no existente correcto! (Email no encontrado)");
		}
	}
	
	public void testPasswordIncorrecta() throws Exception{
		logger.traceEntry("Testing autentificación con password incorrecta");
		Usuario user = usuarioService.autentificar("johnnrodriguez0112@gmail.com", "holakase");
		if(user!=null) {
			logger.info("fallo en el método de auntetificación");
		} else {
			logger.info("Autentificación con password incorrecta (Password incorrecta)");
		}
	}
	
	public void testDelete() throws Exception{
		logger.info("Testing delete usuario...");
		Long id = 10l;
		if(!usuarioService.delete(id)) {
			logger.info("error al eliminar el usuario");
		} else {
			logger.info("Usuario dado de baja correctamente");
		}
	}
	
	public void testFindById() throws Exception{
		logger.info("Testing findById...");
		Long id = 2l;
		Usuario u = null;
		u = usuarioService.findById(id);
		if(u!=null) {
			logger.info("Usuario con id "+id+" encontrado: ");
			logger.info(u);
		} else {
			logger.info("No se ha encontrado el usuario con id "+id);
		}
	}
	
	public void testFindByEmail() throws Exception{
		logger.info("Testing findByEmail...");
		String email = "manuel.garcia@email.com";
		Usuario u = null;
		u = usuarioService.findByEmail(email);
		if(u!=null) {
			logger.info("Usuario con email "+email+" encontrado: ");
			logger.info(u);
		} else {
			logger.info("No se ha encontrado el usuario con email "+email);
		}
	}
	
	public void testFindByAll() throws Exception{
		logger.traceEntry("Testing findByAll...");
		List<Usuario> usuarios = null;
		usuarios = usuarioService.findByAll();
		if(usuarios.isEmpty()) {
			logger.info("No se han encontrado usuarios");
		} else {
			logger.info("Usuarios encontrados: ");
			logger.info(usuarios);
		}
	}
	
	public void testUpdatePassword() throws Exception{
		logger.traceEntry("Testing update password...");
		String password = "abc123456.@";
		Long id = 2l;
		if(usuarioService.updatePassword(password, id)){
			logger.info("Contraseña actualizada correctamente!");
		} else {
			logger.info("Error al actualizar la contraseña");
		}
	}
	
	public static void main(String []args) throws Exception{
		UsuarioServiceTest test = new UsuarioServiceTest();
		test.testRegistrar();
		test.testAutentificacion();
		test.testEmailInexistente();
		test.testPasswordIncorrecta();
		test.testDelete();
		test.testFindById();
		test.testFindByEmail();
		test.testFindByAll();
		test.testUpdatePassword();
	}
}
