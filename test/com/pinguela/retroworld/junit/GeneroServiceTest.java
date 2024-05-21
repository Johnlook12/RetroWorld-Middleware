package com.pinguela.retroworld.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import com.pinguela.retroworld.model.Genero;
import com.pinguela.retroworld.service.GeneroService;
import com.pinguela.retroworld.service.impl.GeneroServiceImpl;

public class GeneroServiceTest {
	private GeneroService generoService = null;
	
	public GeneroServiceTest() {
		generoService = new GeneroServiceImpl();
	}
	@Test
	public void testFindByAll() throws Exception{
		List<Genero>generos = generoService.findByAll();
		assertEquals(10, generos.size());
	}

}
