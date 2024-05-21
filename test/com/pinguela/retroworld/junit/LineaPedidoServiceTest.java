package com.pinguela.retroworld.junit;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.pinguela.retroworld.model.LineaPedido;
import com.pinguela.retroworld.service.LineaPedidoService;
import com.pinguela.retroworld.service.impl.LineaPedidoServiceImpl;

public class LineaPedidoServiceTest {
	private LineaPedidoService lineaPedidoService=null;
	
	public LineaPedidoServiceTest() {
		lineaPedidoService = new LineaPedidoServiceImpl();
	}
	
	@Test
	public void testFindById01() throws Exception{
		Long id=1l;
		LineaPedido lp=lineaPedidoService.findById(id);
		assertEquals(id, lp.getId());
	}
	
	@Test
	public void testFindById02()throws Exception{
		Long id=-1l;
		LineaPedido lp=lineaPedidoService.findById(id);
		assertNull(lp);
	}

}
