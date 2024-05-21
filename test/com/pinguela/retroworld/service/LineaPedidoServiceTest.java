package com.pinguela.retroworld.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.LineaPedido;
import com.pinguela.retroworld.service.impl.LineaPedidoServiceImpl;

public class LineaPedidoServiceTest {
	private static Logger logger = LogManager.getLogger(LineaPedidoServiceTest.class);
	private LineaPedidoService lineaPedidoService = null;
	
	public LineaPedidoServiceTest() {
		lineaPedidoService = new LineaPedidoServiceImpl();
	}
	
	public void testFindById() throws Exception{
		logger.traceEntry("Testing FindById...");
		LineaPedido lp = null;
		Long id = 3l;
		lp = lineaPedidoService.findById(id);
		if(lp!=null) {
			logger.info("Linea encontrada: ");
			logger.info(lp);
		} else {
			logger.info("Linea no encontrada");
		}
	}
	
	public static void main(String[]args) throws Exception{
		LineaPedidoServiceTest test = new LineaPedidoServiceTest();
		test.testFindById();
	}
}
