package com.pinguela.retroworld.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Provincia;
import com.pinguela.retroworld.service.impl.ProvinciaServiceImpl;

public class ProvinciaServiceTest {
	private static Logger logger = LogManager.getLogger(ProvinciaServiceTest.class);
	private ProvinciaService provinciaService = null;
	
	public ProvinciaServiceTest() {
		provinciaService = new ProvinciaServiceImpl();
	}
	
	public void testFindByIdPais() throws Exception{
		logger.traceEntry("Testing findByIdPais...");
		List<Provincia> provincias = null;
		Short idPais = 64;
		provincias = provinciaService.findByIdPais(idPais);
		if(provincias.isEmpty()) {
			logger.info("Provincias no encontradas para el pais con id "+idPais);
		} else {
			logger.info("Provincias encontradas para el pais con id "+idPais+": ");
			logger.info(provincias);
		}
	}
	
	public void testFindById() throws Exception{
		logger.traceEntry("Testing findById...");
		Provincia p = null;
		Short id = 11;
		p = provinciaService.findById(id);
		if(p!=null){
			logger.info("Provincia con id "+id+" encontrada: ");
			logger.info(p);
		} else {
			logger.info("Provincia con id "+id+" no encontrada");
		}
	}
	
	public static void main(String[]args) throws Exception{
		ProvinciaServiceTest test = new ProvinciaServiceTest();
		test.testFindByIdPais();
		test.testFindById();
	}
}
