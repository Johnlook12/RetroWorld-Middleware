package com.pinguela.retroworld.service;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Modificacion;
import com.pinguela.retroworld.service.impl.ModificacionServiceImpl;

public class ModificacionServiceTest {
	private static Logger logger = LogManager.getLogger(ModificacionServiceTest.class);
	private ModificacionService modificacionService = null;
	
	public ModificacionServiceTest() {
		modificacionService = new ModificacionServiceImpl();
	}
	
	public void testFindByIdEmpleado() throws Exception{
		logger.traceEntry("Testing findByIdEmpleado...");
		List<Modificacion> modificaciones = null;
		Long idEmpleado = 2l;
		modificaciones = modificacionService.findByIdEmpleado(idEmpleado);
		if(modificaciones.isEmpty()) {
			logger.info("Modificaciones no encontradas para el empleado con id "+idEmpleado);
		} else { 
			logger.info("Modificaciones encontradas para el empleado con id "+idEmpleado+": ");
			logger.info(modificaciones);
		}
	}
	
	public void testFindByTipoModificacion() throws Exception{
		logger.traceEntry("Testing findByTipoModificación...");
		List<Modificacion> modificaciones = null;
		Short idTipo = 1;
		modificaciones = modificacionService.findByTipoModificacion(idTipo);
		if(modificaciones.isEmpty()) {
			logger.info("Modificaciones no encontradas del tipo con id "+idTipo);
		} else {
			logger.info("Modificaciones encontradas: ");
			logger.info(modificaciones);
		}
	}
	
//	public void testCreate() throws Exception{
//		logger.traceEntry("Testing create...");
//		Modificacion m = new Modificacion();
//		m.setFecha(new Date());
//		m.setIdAnuncio(2l);
//		m.setIdEmpleado(2l);
//		m.setIdTipoModificacion(3);
//		modificacionService.create(m);
//		logger.info("Modificacion creada correctamente");
//	}
	
	public static void main(String[] args) throws Exception{
		ModificacionServiceTest test = new ModificacionServiceTest();
		test.testFindByIdEmpleado();
		test.testFindByTipoModificacion();
//		test.testCreate();
	}
}
