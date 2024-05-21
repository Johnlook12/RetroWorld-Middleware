package com.pinguela.retroworld.service;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Direccion;
import com.pinguela.retroworld.service.impl.DireccionServiceImpl;

public class DireccionServiceTest {
	private DireccionService direccionService= null;
	private static Logger logger = LogManager.getLogger(DireccionServiceTest.class);
	
	public DireccionServiceTest() {
		direccionService = new DireccionServiceImpl();
	}
	
	public void testCreate() throws Exception{
		logger.traceEntry("Testing create...");
		Direccion d = new Direccion();
		d.setCodigoPostal(27400l);
		d.setDirVia("13");
		d.setIdUsuario(3l);
		d.setLetra("C");
		d.setTipoVia("Rúa");
		d.setNombreVia("Central");
		d.setPiso(3);
		
		direccionService.create(d);
		logger.info("Direccion creada correctamente");
		}
	
	public void testUpdate() throws Exception{
		logger.traceEntry("Testing update...");
		Long id = 19l;
		Direccion d = direccionService.findById(id);
		d.setPiso(2);
		d.setLetra("A");
		if(!direccionService.update(d)) {
			logger.info("Error al actualizar la dirección");
		} else {
			logger.info("Dirección actualizada correctamente");
		}
	}
	
	public void testFindById() throws Exception{
		logger.traceEntry("Testing findById...");
		Direccion d = null;
		Long id = 9l;
		d = direccionService.findById(id);
		if(d!=null) {
			logger.info("Dirección con id "+id+" encontrada: ");
			logger.info(d);
		} else {
			logger.info("No se ha encontrado la direccion con id "+id);
		}
	}
	
	public void testFindByIdUsuario() throws Exception{
		logger.traceEntry("Testing findByIdUsuario...");
		List<Direccion> direcciones = null;
		Long id = 2l;
		direcciones = direccionService.findByIdUsuario(id);
		if(direcciones.isEmpty()) {
			logger.info("Direcciones no encontradas para el usuario con id "+id);
		} else {
			logger.info("Direcciones del usuario con id "+id+" encontradas: ");
			logger.info(direcciones);
		}
	}
	
	public void testFindByAll() throws Exception{
		logger.traceEntry("Testing findByAll...");
		List<Direccion> direcciones = null;
		direcciones = direccionService.findByAll();
		if(direcciones.isEmpty()) {
			logger.info("Direcciones no encontradas");
		} else {
			logger.info("Direcciones encontradas: ");
			logger.info(direcciones);
		}
	}
	
	public static void main (String[]args) throws Exception{
		DireccionServiceTest test = new DireccionServiceTest();
		test.testFindById();
		test.testFindByAll();
		test.testFindByIdUsuario();
		test.testCreate();
		test.testUpdate();
	}
}
