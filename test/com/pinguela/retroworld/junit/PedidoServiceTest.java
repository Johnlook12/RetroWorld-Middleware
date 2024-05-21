package com.pinguela.retroworld.junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.pinguela.retroworld.model.LineaPedido;
import com.pinguela.retroworld.model.Pedido;
import com.pinguela.retroworld.service.PedidoService;
import com.pinguela.retroworld.service.impl.PedidoServiceImpl;

public class PedidoServiceTest {
	private PedidoService pedidoService = null;
	
	public PedidoServiceTest() {
		pedidoService= new PedidoServiceImpl();
	}

	@Test
	public void testCreate() throws Exception{
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
		assertNotNull(p.getId());
	}
	
	@Test
	public void testFindById01() throws Exception{
		Long id = 2l;
		Pedido p = pedidoService.findById(id);
		assertEquals(id, p.getId());
	}
	
	@Test 
		public void testFindById02() throws Exception{
		Long id = -1l;
		Pedido p = pedidoService.findById(id);
		assertNull(p);
	}
	
	@Test
	public void testFindByUsuario01()throws Exception{
		Long idUsuario = 2l;
		List<Pedido>pedidos = pedidoService.findByUsuario(idUsuario);
		for(Pedido p:pedidos) {
			assertEquals(idUsuario, p.getIdUsuario());
		}
	}
	
	@Test
	public void testFindByUsuario02()throws Exception{
		Long idUsuario = -2l;
		List<Pedido>pedidos = pedidoService.findByUsuario(idUsuario);
		assertTrue(pedidos.isEmpty());
	}
	
	@Test 
	public void testUpdate() throws Exception{
		Long id= 2l;
		LineaPedido lp = new LineaPedido();
		Pedido p=pedidoService.findById(id);
		lp.setIdVideojuego(3l);
		lp.setPrecio(50.00d);
		List<LineaPedido> lineas = p.getLineas();
		lineas.add(lp);
		p.setIdEstado(1);
		boolean pedidoUpdated = pedidoService.update(p);
		assertTrue(pedidoUpdated);
	}
}
