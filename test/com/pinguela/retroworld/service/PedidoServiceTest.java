package com.pinguela.retroworld.service;


import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.LineaPedido;
import com.pinguela.retroworld.model.Pedido;
import com.pinguela.retroworld.service.impl.PedidoServiceImpl;

public class PedidoServiceTest {
	private PedidoService pedidoService = null;
	private static Logger logger = LogManager.getLogger(PedidoServiceTest.class);	
	
	public PedidoServiceTest() {
		pedidoService = new PedidoServiceImpl();
	}
	
	public void testCreate() throws Exception{
		logger.traceEntry("Testing create...");
		Pedido p = new Pedido();
		LineaPedido lp = new LineaPedido();
		LineaPedido lp2 = new LineaPedido();
		
		p.setFecha(new Date());
		p.setIdEstado(1);
		p.setIdUsuario(4l);
		
		lp.setIdVideojuego(34l);
		lp.setPrecio(30.0d);
		p.getLineas().add(lp);
		
		lp2.setIdVideojuego(40l);
		lp2.setPrecio(15.99d);
		p.getLineas().add(lp2);
		
		pedidoService.create(p);
		
		logger.info("Pedido con id "+p.getId()+" creado correctamente");
	}
	
	public void testFindById() throws Exception{
		logger.traceEntry("Testing findById...");
		Pedido p = null;
		Long id = 21l;
		p = pedidoService.findById(id);
		if(p!=null) {
			logger.info("Pedido encontrado: ");
			logger.info(p);
		} else {
			logger.info("Pedido con id "+id+" no encontrado");
		}
	}
	
	public void testFindByUsuario() throws Exception{
		logger.traceEntry("Testing findByUsuario...");
		List<Pedido> pedidos= null;
		Long id = 2l;
		pedidos = pedidoService.findByUsuario(id);
		if(pedidos.isEmpty()) {
			logger.info("Pedidos para el usuario "+id+" no encontrados");
		} else {
			logger.info("Pedidos encontrados: ");
			logger.info(pedidos);
		}
	}
	
	public void testUpdate() throws Exception{
		logger.traceEntry("Testing update...");
		Long id = 2l;
		Pedido p = null;
		LineaPedido lp = new LineaPedido();
		p=pedidoService.findById(id);
		lp.setIdVideojuego(3l);
		lp.setPrecio(50.00d);
		List<LineaPedido> lineas = p.getLineas();
		lineas.add(lp);
		p.setIdEstado(1);
		if(!pedidoService.update(p)) {
			logger.info("Error al actualizar el pedido");
		} else {
			logger.info("Pedido actualizado correctamente");
		}
	}

	
	public static void main (String[]args) throws Exception{
		PedidoServiceTest test = new PedidoServiceTest();
		test.testCreate();
		test.testFindById();
		test.testFindByUsuario();
		test.testUpdate();
	}
}
