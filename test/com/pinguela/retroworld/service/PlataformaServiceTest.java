package com.pinguela.retroworld.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Plataforma;
import com.pinguela.retroworld.service.impl.PlataformaServiceImpl;

public class PlataformaServiceTest {
	private static Logger logger = LogManager.getLogger(PlataformaServiceTest.class);
	private PlataformaService plataformaService = null;
	
	public PlataformaServiceTest() {
		plataformaService = new PlataformaServiceImpl();
	}
	
	public void testFindByAll() throws Exception{
		logger.traceEntry("Testing findByAll...");
		List<Plataforma> plataformas = null;
		plataformas = plataformaService.findByAll();
		if(plataformas.isEmpty()) {
			logger.info("No se han encontrado plataformas");
		} else {
			logger.info("Plataformas encontradas: ");
			logger.info(plataformas);
		}
	}
	
	public static void main(String[]args) throws Exception{
		PlataformaServiceTest test = new PlataformaServiceTest();
		test.testFindByAll();
	}
}
