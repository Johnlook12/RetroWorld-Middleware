package com.pinguela.retroworld.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Localidad;
import com.pinguela.retroworld.service.impl.LocalidadServiceImpl;

public class LocalidadServiceTest {
	private static Logger logger = LogManager.getLogger(LocalidadServiceTest.class);
	private LocalidadService localidadService = null;
	
	public LocalidadServiceTest() {
		localidadService = new LocalidadServiceImpl();
	}
	
	public void testfindById() throws Exception{
		Localidad l = null;
		// el id de la Localidad es el cp
		Long id = 27400l;
		l = localidadService.findById(id);
		if(l!=null) {
			logger.info("Localidad encontrada: ");
			logger.info(l);
		} else {
			logger.info("Error al encontrar la localidad");
		}
	}
	
	public void testFindByProvincia() throws Exception{
		List<Localidad> localidades = null;
		Short idProvincia = 27;
		localidades = localidadService.findByIdProvincia(idProvincia);
		if(localidades.isEmpty()) {
			logger.info("Localidades no encontradas para la provincia con id "+idProvincia);
		} else { 
			logger.info("Localidades encontradas para la provincia con id "+idProvincia+" : ");
			logger.info(localidades);
		}
	}
	
	public static void main(String[] args) throws Exception{
		LocalidadServiceTest test = new LocalidadServiceTest();
		test.testfindById();
		test.testFindByProvincia();
	}
}
