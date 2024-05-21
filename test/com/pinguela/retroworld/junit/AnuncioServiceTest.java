package com.pinguela.retroworld.junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Date;

import org.junit.Test;

import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.model.Modificacion;
import com.pinguela.retroworld.model.Results;
import com.pinguela.retroworld.service.AnuncioCriteria;
import com.pinguela.retroworld.service.AnuncioService;
import com.pinguela.retroworld.service.impl.AnuncioServiceImpl;

public class AnuncioServiceTest {
	private AnuncioService anuncioService=null;
	
	public AnuncioServiceTest() {
		anuncioService = new AnuncioServiceImpl();
	}
	
	@Test
	public void testCreate() throws Exception{
		Anuncio a = new Anuncio();
		a.setTitulo("Venta The Witcher 3 a buen precio");
		a.setDescripcion("The witcher 3 en buen estado");
		a.setFechaInicio(new Date());
		a.setFechaFin(new Date());
		a.setPrecio(30.0d);
		a.setIdVideojuego(19l);
		a.setIdUsuario(1l);
		a.setIdEmpleado(null);
		a.setEstadoVideojuego(1);
		a.setIdEstadoAnuncio(2);
		
		anuncioService.create(a);
		assertNotNull(a.getId());
	}
	
	@Test
	public void testUpdate()throws Exception{
		Modificacion m = new Modificacion();
		
		Long id = 4l;
		Anuncio a = anuncioService.findById(id);
		a.setTitulo("Super Street Fighter II Oferta!!");
		
		m.setFecha(new Date());
		m.setIdAnuncio(a.getId());
		m.setIdEmpleado(a.getIdEmpleado());
		m.setIdTipoModificacion(4);
		
//		boolean anuncioUpdated=anuncioService.update(a, m);
//		assertTrue(anuncioUpdated);
	}
	
	@Test
	public void testFindByTitulo() throws Exception{
		AnuncioCriteria criteria = new AnuncioCriteria();
		criteria.setNombre("hyrule");
		Results <Anuncio> anuncios = anuncioService.findBy(criteria, 1, 200);
		assertNotEquals(0, anuncios.getPage().size());
	}
	
	@Test
	public void testFindByDescripcion()throws Exception{
		AnuncioCriteria criteria = new AnuncioCriteria();
		criteria.setDescripcion("aventura");
		Results<Anuncio> anuncios = anuncioService.findBy(criteria, 1, 200);
		assertNotEquals(0, anuncios.getPage().size());
	}
	
	@Test
	public void testFindByIdEstadoAnuncio()throws Exception{
		AnuncioCriteria criteria = new AnuncioCriteria();
		Integer idEstado = 1;
		criteria.setIdEstadoAnuncio(idEstado);
		Results<Anuncio> anuncios = anuncioService.findBy(criteria, 1, 200);
		for(Anuncio a:anuncios.getPage()) {
			assertEquals(idEstado, a.getIdEstadoAnuncio());
		}
	}
	
	@Test
	public void testFindByPrecioDesdeHasta()throws Exception{
		AnuncioCriteria criteria = new AnuncioCriteria();
		criteria.setPrecioDesde(0.0d);
		criteria.setPrecioHasta(50.0d);
		Results<Anuncio> anuncios = anuncioService.findBy(criteria, 1, 200);
		for(Anuncio a:anuncios.getPage()) {
			assertTrue(a.getPrecio()>0 && a.getPrecio()<=50);
		}
	}
	
	@Test
	public void testFindByNombreVideojuego()throws Exception{
		AnuncioCriteria criteria = new AnuncioCriteria();
		criteria.setNombreVideojuego("Zelda");;
		Results <Anuncio> anuncios = anuncioService.findBy(criteria, 1, 200);
		assertNotEquals(0, anuncios.getPage().size());
	}
	
	@Test
	public void testFindByMultipleParameters()throws Exception{
		AnuncioCriteria criteria = new AnuncioCriteria();
		criteria.setNombreVideojuego("zelda");
		criteria.setPrecioHasta(50.0d);
		criteria.setDescripcion("hyrule");
		Results <Anuncio> anuncios = anuncioService.findBy(criteria, 1, 200);
		assertNotEquals(0, anuncios.getPage().size());
	}
	
	@Test
	public void testFindByUsuarioExistente()throws Exception{
		Long idUsuario=1l;
		AnuncioCriteria criteria = new AnuncioCriteria();
		criteria.setIdUsuario(idUsuario);
		Results <Anuncio> anuncios = anuncioService.findBy(criteria, 1, 200);
		for(Anuncio a:anuncios.getPage()) {
			assertEquals(idUsuario, a.getIdUsuario());
		}
	}
	
	@Test
	public void testFindByUsuarioInexistente()throws Exception{
		Long idUsuario=-1l;
		AnuncioCriteria criteria = new AnuncioCriteria();
		criteria.setIdUsuario(idUsuario);
		Results <Anuncio> anuncios = anuncioService.findBy(criteria, 1, 200);
		assertTrue(anuncios.getPage().isEmpty());
	}
	
	@Test
	public void testOrderBy()throws Exception{
		AnuncioCriteria criteria = new AnuncioCriteria();
		criteria.setOrderBy(AnuncioCriteria.ORDER_BY_FECHA_INICIO);
		criteria.setAscDesc(Boolean.TRUE);
		Results <Anuncio> anuncios = anuncioService.findBy(criteria, 1, 200);
		assertNotEquals(0, anuncios.getPage().size());
	}
	
	@Test
	public void testFindByIdExistente()throws Exception{
		Long id = 20l;
		Anuncio a = anuncioService.findById(id);
		assertEquals(id, a.getId());
	}
	
	@Test
	public void testFindByIdInexistente()throws Exception{
		Long id = -20l;
		Anuncio a = anuncioService.findById(id);
		assertNull(a);
	}
	
	@Test
	public void testFindByGenero()throws Exception{
		AnuncioCriteria criteria = new AnuncioCriteria();
		Integer idGenero=1;
		criteria.setIdGeneroVideojuego(idGenero);
		Results<Anuncio> anuncios = anuncioService.findBy(criteria, 1, 200);
		assertNotEquals(0, anuncios.getPage().size());
	}
	
	@Test
	public void testFindByAll()throws Exception{
		AnuncioCriteria criteria = new AnuncioCriteria();
		Results <Anuncio> anuncios = anuncioService.findBy(criteria, 1, 200);
		assertNotEquals(0, anuncios.getPage().size());
	}
}
