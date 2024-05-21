package com.pinguela.retroworld.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Direccion;
import com.pinguela.retroworld.model.Empleado;
import com.pinguela.retroworld.service.impl.EmpleadoServiceImpl;

public class EmpleadoServiceTest {
	
	private static Logger logger = LogManager.getLogger(EmpleadoServiceTest.class);
	
	private EmpleadoService empleadoService = null;
	
	public EmpleadoServiceTest() {
		empleadoService = new EmpleadoServiceImpl();
	}
	
	
	public void testAutentificacion() {
		try {
			logger.traceEntry("Testing autentificación usuario y password");
			Empleado empleado = empleadoService.autentificar("lucia.jimenez@email.com", "clave123");
			if(empleado!=null) {
				logger.trace("Autentificación correcta!!!!");
				logger.trace(empleado);
			} else {
				logger.trace("fallo en la autentificación con usuario y password");
			}
		}catch(DataException e) {
			logger.error("Autentificación fallida: ", e);
		}
		
	}
	
	public void testRegistrar() throws Exception{
		logger.traceEntry("Testing registrar empleado...");
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
		e.setApellido1("Jímenez");
		e.setDocumentoIdentidad("00000000");
		e.setEmail("ja01@gmail.com");
		e.setTelefono("9128382");
		e.setPassword("abc123.");
		empleadoService.registrar(e);
		
		logger.info("Empleado registrado correctamente!");
	}
	
	public void testSoftDelete() throws Exception{
		logger.traceEntry("Testing delete empleado...");
		Long id = 11l;
		if(!empleadoService.delete(id)) {
			logger.info("error al eliminar el empleado");
		} else {
			logger.info("Empleado dado de baja correctamente");
		}
	}
	
	public void testFindById() throws Exception{
		logger.traceEntry("Testing findById...");
		Empleado e = null;
		Long id = 2l;
		e = empleadoService.findById(id);
		if(e!=null) {
			logger.info("empleado encontrado: ");
			logger.info(e);
		} else {
			logger.info("empleado con id "+id+" no encontrado");
		}
	}
	
	public void testFindByAll() throws Exception{
		logger.traceEntry("Testing findByAll...");
		List<Empleado> empleados= null;
		empleados = empleadoService.findByAll();
		if(empleados.isEmpty()) {
			logger.info("No se han encontrado empleados");
		} else {
			logger.info("empleados encontrados: ");
			logger.info(empleados);
		}
	}
	
	public void testFindByEmail() throws Exception{
		logger.traceEntry("Testing findByEmail...");
		Empleado e = null;
		String email = "lucia.jimenez@email.com";
		e = empleadoService.findByEmail(email);
		if(e!=null) {
			logger.info("empleado encontrado: ");
			logger.info(e);
		} else {
			logger.info("El empleado con email "+email+" no existe");
		}
	}
	
	public void testUpdate() throws Exception{
		logger.traceEntry("Testing empleado update...");
		Empleado e = null;
		Long id = 1l;
		e = empleadoService.findById(id);
		e.setNombre("Pedro");
		if(empleadoService.update(e)) {
			logger.info("Empleado actualizado correctamente!");
		} else {
			logger.info("Error al actualizar el empleado");
		}
	}
	
	public void testUpdatePassword() throws Exception{
		logger.traceEntry("Testing update password...");
		String password = "clave123";
		Long id = 6l;
		if(empleadoService.updatePassword(password, id)){
			logger.info("Contraseña actualizada correctamente!");
		} else {
			logger.info("Error al actualizar la contraseña");
		}
	}
	
	public static void main(String[] args) throws Exception{
		EmpleadoServiceTest test = new EmpleadoServiceTest();
//		test.testRegistrar();
//		test.testSoftDelete();
//		test.testAutentificacion();
//		test.testFindById();
//		test.testFindByEmail();
//		test.testFindByAll();
//		test.testUpdate();
		test.testUpdatePassword();
	}

}
