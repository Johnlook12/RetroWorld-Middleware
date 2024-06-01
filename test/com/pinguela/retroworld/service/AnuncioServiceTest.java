package com.pinguela.retroworld.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.model.Modificacion;
import com.pinguela.retroworld.model.Results;
import com.pinguela.retroworld.service.impl.AnuncioServiceImpl;


public class AnuncioServiceTest{
	
	private AnuncioService anuncioService = null;
	private static Logger logger = LogManager.getLogger(AnuncioServiceTest.class);
	
	public AnuncioServiceTest() {
		anuncioService = new AnuncioServiceImpl();
	}
	
	public void testCreate() throws Exception{
		logger.traceEntry("Testing create...");
		Anuncio a = new Anuncio();
		a.setTitulo("Venta The Witcher 3 a buen precio");
		a.setDescripcion("The witcher 3 en buen estado");
		a.setFechaInicio(new Date());
		a.setFechaFin(null);
		a.setPrecio(30.0d);
		a.setIdVideojuego(19l);
		a.setIdUsuario(1l);
		a.setIdEmpleado(null);
		a.setEstadoVideojuego(1);
		a.setIdEstadoAnuncio(2);
		
		anuncioService.create(a);
		logger.info("Guardando el anuncio"+a);
		
	}
	
	public void testUpdate() throws Exception{
		logger.traceEntry("Testing update...");
		List<Modificacion> modificaciones = new ArrayList<Modificacion>();
		Anuncio a = null;
		Modificacion m = new Modificacion();
		
		Long id = 4l;
		a = anuncioService.findById(id);
		a.setTitulo("Super Street Fighter II Oferta!!");
		
		m.setFecha(new Date());
		m.setIdAnuncio(a.getId());
		m.setIdEmpleado(a.getIdEmpleado());
		m.setIdTipoModificacion(2);
		modificaciones.add(m);
		
		m = new Modificacion();
		m.setFecha(new Date());
		m.setIdAnuncio(a.getId());
		m.setIdEmpleado(a.getIdEmpleado());
		m.setIdTipoModificacion(1);
		modificaciones.add(m);
		
		if(!anuncioService.update(a, modificaciones)) {
			logger.info("Error al actualizar el anuncio");
		} else {
			logger.info("anuncio actualizado correctamente!");
		}
	}
	
	public void testFindByTitulo() throws Exception{
		AnuncioCriteria criteria = new AnuncioCriteria();
		criteria.setNombre("hyrule");
		Results <Anuncio> anuncios = anuncioService.findBy(criteria, 1, 200);
		
		if(anuncios.getPage().isEmpty()) {
			logger.info("No se han encontrado anuncios con el nombre "+criteria.getNombre());
		} else {
			logger.info("Anuncios encontrados: "+anuncios.getTotal());
			for(Anuncio anuncio:anuncios.getPage()) {
				logger.info(anuncio);
			}
		}
	}
	
	public void testFindByDescripcion() throws Exception{
		AnuncioCriteria criteria = new AnuncioCriteria();
		criteria.setDescripcion("aventura");
		Results<Anuncio> anuncios = anuncioService.findBy(criteria, 1, 200);
		
		if(anuncios.getPage().isEmpty()) {
			logger.info("No se han encontrado anuncios con la descripción "+criteria.getDescripcion());
		} else {
			logger.info("Anuncios encontrados: "+anuncios.getTotal());
			for(Anuncio anuncio:anuncios.getPage()) {
				logger.info(anuncio);
			}
		}
	}
	
	public void testFindByIdEstadoAnuncio() throws Exception{
		AnuncioCriteria criteria = new AnuncioCriteria();
		criteria.setIdEstadoAnuncio(1);
		Results<Anuncio> anuncios = anuncioService.findBy(criteria, 1, 200);
		
		if(anuncios.getPage().isEmpty()) {
			logger.info("No se han encontrado anuncios con los estados con id "+criteria.getIdEstadoAnuncio());
		} else {
			logger.info("Anuncios encontrados: "+anuncios.getTotal());
			for(Anuncio anuncio:anuncios.getPage()) {
				logger.info(anuncio);
			}
		}
	}
	
	public void testFindByPrecioDesdeHasta() throws Exception{
		AnuncioCriteria criteria = new AnuncioCriteria();
		criteria.setPrecioDesde(0.0d);
		criteria.setPrecioHasta(50.0d);
		Results<Anuncio> anuncios = anuncioService.findBy(criteria, 1, 200);
		
		if(anuncios.getPage().isEmpty()) {
			logger.info("No se han encontrado anuncios con precios desde "+criteria.getPrecioDesde()+" hasta "+criteria.getPrecioDesde());
		} else {
			logger.info("Anuncios encontrados: "+anuncios.getTotal());
			for(Anuncio anuncio:anuncios.getPage()) {
				logger.info(anuncio);
			}
		}
	}
	
	public void testFindByNombreVideojuego() throws Exception{
		AnuncioCriteria criteria = new AnuncioCriteria();
		criteria.setNombreVideojuego("Zelda");;
		Results <Anuncio> anuncios = anuncioService.findBy(criteria, 1, 200);
		
		if(anuncios.getPage().isEmpty()) {
			logger.info("No se han encontrado anuncios con el videojuego "+criteria.getNombreVideojuego());
		} else {
			logger.info("Anuncios encontrados: "+anuncios.getTotal());
			for(Anuncio anuncio:anuncios.getPage()) {
				logger.info(anuncio);
			}
		}
	}
	
	public void testFindByMultipleParameters() throws Exception{
		AnuncioCriteria criteria = new AnuncioCriteria();
		criteria.setNombreVideojuego("zelda");
		criteria.setPrecioHasta(50.0d);
		criteria.setDescripcion("hyrule");
		Results <Anuncio> anuncios = anuncioService.findBy(criteria, 1, 200);
		
		if(anuncios.getPage().isEmpty()) {
			logger.info("No se han encontrado anuncios");
		} else {
			logger.info("Anuncios encontrados: "+anuncios.getTotal());
			for(Anuncio anuncio:anuncios.getPage()) {
				logger.info(anuncio);
			}
		}
	}
	
	public void testFindByUsuario() throws Exception{
		AnuncioCriteria criteria = new AnuncioCriteria();
		criteria.setIdUsuario(1l);
		Results <Anuncio> anuncios = anuncioService.findBy(criteria, 1, 200);
		
		if(anuncios.getPage().isEmpty()) {
			logger.info("No se han encontrado anuncios con el usuario con id "+criteria.getIdUsuario());
		} else {
			logger.info("Anuncios encontrados: "+anuncios.getTotal());
			for(Anuncio anuncio:anuncios.getPage()) {
				logger.info(anuncio);
			}
		}
	}
	
	public void testOrderBy() throws Exception{
		AnuncioCriteria criteria = new AnuncioCriteria();
		criteria.setOrderBy(AnuncioCriteria.ORDER_BY_FECHA_INICIO);
		criteria.setAscDesc(Boolean.TRUE);
		Results <Anuncio> anuncios = anuncioService.findBy(criteria, 1, 200);
		
		if(anuncios.getPage().isEmpty()) {
			logger.info("No se han encontrado anuncios");
		} else { 
			logger.info("Anuncios encontrados: "+anuncios.getTotal());
			for(Anuncio anuncio:anuncios.getPage()) {
				logger.info(anuncio);
			}
		}
	}
	
	public void testFindById() throws Exception{
		Anuncio a = null;
		Long id = 20l;
		a = anuncioService.findById(id);
		if(a!=null) {
			logger.info("Anuncio con id "+id+" encontrado: ");
			logger.info(a);
		} else {
			logger.info("No se ha encontrado el anuncio con id "+id);
		}
	}
	
	public void testFindByGenero() throws Exception{
		AnuncioCriteria criteria = new AnuncioCriteria();
		criteria.setIdGeneroVideojuego(1);
		Results<Anuncio> anuncios = anuncioService.findBy(criteria, 1, 200);
		
		if(anuncios.getPage().isEmpty()) {
			logger.info("No se han encontrado anuncios con el genero con id "+criteria.getIdGeneroVideojuego());
		} else {
			logger.info("Anuncios encontrados: "+anuncios.getTotal());
			for(Anuncio anuncio:anuncios.getPage()) {
				logger.info(anuncio);
			}
		}
	}
	
	public void testFindByAll() throws Exception{
		AnuncioCriteria criteria = new AnuncioCriteria();
		Results <Anuncio> anuncios = anuncioService.findBy(criteria, 1, 200);
		
		if(anuncios.getPage().isEmpty()) {
			logger.info("No se han encontrado anuncios");
		} else { 
			logger.info("Anuncios encontrados: "+anuncios.getTotal());
			for(Anuncio anuncio:anuncios.getPage()) {
				logger.info(anuncio);
			}
		}
	}
	
	public static void main(String []args) throws Exception{
		AnuncioServiceTest test = new AnuncioServiceTest();
		test.testCreate();
//		test.testFindById();
//		test.testFindByTitulo();
//		test.testFindByDescripcion();
//		test.testFindByMultipleParameters();
//		test.testFindByPrecioDesdeHasta();
//		test.testUpdate();
//		test.testFindByUsuario();
//		test.testOrderBy();
//		test.testFindByNombreVideojuego();
//		test.testFindByIdEstadoAnuncio();
//		test.testFindByAll();
//		test.testFindByGenero();
	}
}
