package com.pinguela.retroworld.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Pais;
import com.pinguela.retroworld.service.impl.PaisServiceImpl;

public class PaisServiceTest {
	private static Logger logger = LogManager.getLogger(PaisServiceTest.class);
	private PaisService paisService = null;
	
	public PaisServiceTest() {
		paisService = new PaisServiceImpl();
	}
	
	public void testFindByAll() throws Exception{
		logger.traceEntry("Testing findByAll...");
		List<Pais> paises = null;
		paises = paisService.findByAll();
		if(paises.isEmpty()) {
			logger.info("Paises no encontrados");
		} else {
			logger.info("Paises encontrados: ");
			logger.info(paises);
		}
	}
	
	public void testFindById() throws Exception{
		logger.traceEntry("Testing findById...");
		Pais pais = null;
		Integer id = 44;
		pais = paisService.findById(id);
		if(pais!=null) {
			logger.info("Pais con id "+id+" encontrado: ");
			logger.info(pais);
		} else {
			logger.info("Pais con id "+id+" no encontrado");
		}
	}
	
	public static void main(String[]args) throws Exception{
		PaisServiceTest test= new PaisServiceTest();
		test.testFindByAll();
		test.testFindById();
	}
}
