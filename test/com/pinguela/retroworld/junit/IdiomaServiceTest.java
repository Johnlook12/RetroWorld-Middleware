package com.pinguela.retroworld.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import com.pinguela.retroworld.model.Idioma;
import com.pinguela.retroworld.service.IdiomaService;
import com.pinguela.retroworld.service.impl.IdiomaServiceImpl;


public class IdiomaServiceTest {
	private IdiomaService idiomaService;

	public IdiomaServiceTest() {
		idiomaService = new IdiomaServiceImpl();
	}
	
	
	
	@Test
	public void testFindByAll() throws Exception{
		List<Idioma> idiomas = idiomaService.findByAll();
		assertEquals(15, idiomas.size());
	}

}
