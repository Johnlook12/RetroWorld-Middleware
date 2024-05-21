package com.pinguela.retroworld.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Idioma;
import com.pinguela.retroworld.service.impl.IdiomaServiceImpl;

public class IdiomaServiceTest {
	private static Logger logger = LogManager.getLogger(IdiomaServiceImpl.class);
	private IdiomaService idiomaService = null;
	
	public IdiomaServiceTest() {
		idiomaService = new IdiomaServiceImpl();
	}
	
	public void testFindByAll() throws Exception{
		List<Idioma> idiomas = null;
		idiomas = idiomaService.findByAll();
		if(idiomas.isEmpty()) {
			logger.info("No se han encontrado idiomas");
		} else {
			logger.info("Idiomas encontrados: ");
			logger.info(idiomas);
		}
	}
	
	public static void main(String[]args) throws Exception{
		IdiomaServiceTest test = new IdiomaServiceTest();
		test.testFindByAll();
	}
}
