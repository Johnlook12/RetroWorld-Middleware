package com.pinguela.retroworld.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Genero;
import com.pinguela.retroworld.service.impl.GeneroServiceImpl;

public class GeneroServiceTest {
	private static Logger logger = LogManager.getLogger(GeneroServiceTest.class);
	private GeneroService generoService = null;
	
	public GeneroServiceTest() {
		generoService = new GeneroServiceImpl();
	}
	
	public void testFindByAll() throws Exception{
		List<Genero> generos = null;
		generos = generoService.findByAll();
		if(generos.isEmpty()) {
			logger.info("No se han encontrado géneros");
		} else {
			logger.info("Géneros encontrados: ");
			logger.info(generos);
		}
	}
	
	public static void main(String[]args) throws Exception{
		GeneroServiceTest test = new GeneroServiceTest();
		test.testFindByAll();
	}
}
