package com.pinguela.retroworld.junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.pinguela.retroworld.model.Genero;
import com.pinguela.retroworld.model.Idioma;
import com.pinguela.retroworld.model.Plataforma;
import com.pinguela.retroworld.model.Results;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.service.VideojuegoCriteria;
import com.pinguela.retroworld.service.VideojuegoService;
import com.pinguela.retroworld.service.impl.VideojuegoServiceImpl;
import com.pinguela.retroworld.util.DateUtils;

public class VideojuegoServiceTest {
	private VideojuegoService videojuegoService=null;
	
	public VideojuegoServiceTest(){
		videojuegoService = new VideojuegoServiceImpl();
	}
	
	@Test
	public void testCreateCabecera() throws Exception{
		Videojuego v = new Videojuego();
		v.setNombre("Persona 4");
		v.setFechaLanzamiento(DateUtils.getDate(2008, 07, 10));
		v.setIdDesarrolladora(13);
		v.setDescripcion("videojuego de rol desarrollado y publicado por Atlus para la consola de PlayStation 2, y cronológicamente es la quinta entrega dentro de la serie de Persona");
		videojuegoService.create(v);
		assertNotNull(v.getId());
	}
	
	@Test
	public void testCreateCompleto()throws Exception{
		Videojuego v = new Videojuego();
		Idioma i = new Idioma();
		Genero g = new Genero();
		Plataforma p = new Plataforma();
		List<Idioma> idiomas = new ArrayList<Idioma>();
		List<Genero> generos = new ArrayList<Genero>();
		List<Plataforma> plataformas = new ArrayList<Plataforma>();
		
		i.setId(2);
		idiomas.add(i);
		g.setId(4);
		generos.add(g);
		p.setId(5);
		plataformas.add(p);
		p = new Plataforma();
		p.setId(10);
		plataformas.add(p);
		
		v.setNombre("Shin Megami Tensei IV");
		v.setFechaLanzamiento(DateUtils.getDate(2013, Calendar.MAY, 23));
		v.setIdDesarrolladora(13);
		v.setDescripcion("videojuego de rol japonés postapocalíptico desarrollado por Atlus");
		v.setIdiomas(idiomas);
		v.setGeneros(generos);
		v.setPlataformas(plataformas);
		videojuegoService.create(v);
		assertNotNull(v.getId());
	}
	
	@Test
	public void testUpdate() throws Exception{
		Long id = 3l;
		Videojuego v = videojuegoService.findById(id);
		v.setIdDesarrolladora(2);
		boolean videojuegoUpdated = videojuegoService.update(v);
		assertTrue(videojuegoUpdated);
	}
	
	@Test
	public void testFindById()throws Exception{
		Long id = 2l;
		Videojuego v = videojuegoService.findById(id);
		assertEquals(id, v.getId());
	}
	
	@Test
	public void testFindByIdInexistente()throws Exception{
		Long id = -2l;
		Videojuego v = videojuegoService.findById(id);
		assertNull(v);
	}
	
	@Test
	public void testFindByNombre()throws Exception{
		VideojuegoCriteria criteria = new VideojuegoCriteria();
		criteria.setNombre("Zelda");
		Results <Videojuego> videojuegos = videojuegoService.findBy(criteria, 1, 200);
		assertNotEquals(0, videojuegos.getPage().size());
	}
	
	@Test
	public void testFindByGenero()throws Exception{
		VideojuegoCriteria criteria = new VideojuegoCriteria();
		Integer idGenero=1;
		criteria.setIdGenero(idGenero);
		Results <Videojuego> videojuegos = videojuegoService.findBy(criteria, 1, 200);
		assertNotEquals(0, videojuegos.getPage().size());
	}
	
	@Test
	public void testFindByIdioma()throws Exception{
		VideojuegoCriteria criteria = new VideojuegoCriteria();
		Integer idIdioma = 3;
		criteria.setIdIdioma(idIdioma);
		Results <Videojuego> videojuegos = videojuegoService.findBy(criteria, 1, 200);
		assertNotEquals(0, videojuegos.getPage().size());
	}
	
	@Test
	public void testFindByPlataforma()throws Exception{
		VideojuegoCriteria criteria = new VideojuegoCriteria();
		Integer idPlataforma = 5;
		criteria.setIdPlataforma(idPlataforma);
		Results <Videojuego> videojuegos = videojuegoService.findBy(criteria, 1, 200);
	}
	
	@Test
	public void testFindByDesarrolladora()throws Exception{
		VideojuegoCriteria criteria = new VideojuegoCriteria();
		Integer idDesarrolladora = 4;
		criteria.setIdDesarrolladora(idDesarrolladora);
		Results <Videojuego> videojuegos = videojuegoService.findBy(criteria, 1, 200);
		for(Videojuego v:videojuegos.getPage()) {
			assertEquals(idDesarrolladora, v.getIdDesarrolladora());
		}
	}
	
	@Test
	public void testFindByFechaLanzamiento()throws Exception{
		VideojuegoCriteria criteria = new VideojuegoCriteria();
		Date fechaDesde = DateUtils.getDate(2000, 01, 01);
		Date fechaHasta  = DateUtils.getDate(2005, 12, 31);
		criteria.setFechaLanzamientoDesde(DateUtils.getDate(2000, 01, 01));
		criteria.setFechaLanzamientoHasta(DateUtils.getDate(2005, 12, 31));;
		Results <Videojuego> videojuegos = videojuegoService.findBy(criteria, 1, 200);
		for(Videojuego v:videojuegos.getPage()) {
			Date fechaLanzamiento = v.getFechaLanzamiento();
			assertTrue(fechaLanzamiento.compareTo(fechaDesde)>=0);
			assertTrue(fechaLanzamiento.compareTo(fechaHasta)<=0);
		}
	}
	
	@Test
	public void testFindByMultipleParameters()throws Exception{
		VideojuegoCriteria criteria = new VideojuegoCriteria();
		criteria.setIdIdioma(3);
		criteria.setIdPlataforma(5);
		criteria.setFechaLanzamientoDesde(DateUtils.getDate(2000, 01, 01));
		criteria.setFechaLanzamientoHasta(new Date());
		Results <Videojuego> videojuegos = videojuegoService.findBy(criteria, 1, 200);
		assertNotEquals(0, videojuegos.getPage().size());
	}
	
	@Test
	public void testFindByAll()throws Exception{
		VideojuegoCriteria criteria = new VideojuegoCriteria();
		Results <Videojuego> videojuegos = videojuegoService.findBy(criteria, 1, 200);
		assertNotEquals(0, videojuegos.getPage().size());
	}
}
