package com.pinguela.retroworld.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Desarrolladora;
import com.pinguela.retroworld.service.impl.DesarrolladoraServiceImpl;

public class DesarrolladoraServiceTest {
	
	private static Logger logger = LogManager.getLogger(DesarrolladoraServiceTest.class);
	private DesarrolladoraService desarrolladoraService;
	
	public DesarrolladoraServiceTest() {
		desarrolladoraService = new DesarrolladoraServiceImpl();
	}
	
	public void testFindByAll() throws Exception{
		List<Desarrolladora> desarrolladoras = desarrolladoraService.findByAll();
		if(desarrolladoras.isEmpty()) {
			logger.info("No se han encontrado desarrolladoras");
		} else {
			logger.info("Desarrolladoras encontradas: ");
			logger.info(desarrolladoras);
		}
	}
	
	public static void main(String[]args) throws Exception{
		DesarrolladoraServiceTest test = new DesarrolladoraServiceTest();
		test.testFindByAll();
	}
}
