package com.pinguela.retroworld.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import com.pinguela.retroworld.model.Plataforma;
import com.pinguela.retroworld.service.PlataformaService;
import com.pinguela.retroworld.service.impl.PlataformaServiceImpl;

public class PlataformaServiceTest {
	
	private PlataformaService plataformaService = null;
	
	public PlataformaServiceTest() {
		plataformaService = new PlataformaServiceImpl();
	}
	
	@Test
	public void testFindByAll() throws Exception{
		List<Plataforma>plataformas = plataformaService.findByAll();
		assertEquals(17, plataformas.size());
	}

}
