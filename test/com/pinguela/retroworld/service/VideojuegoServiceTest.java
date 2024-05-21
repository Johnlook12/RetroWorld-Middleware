package com.pinguela.retroworld.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.retroworld.model.Genero;
import com.pinguela.retroworld.model.Idioma;
import com.pinguela.retroworld.model.Plataforma;
import com.pinguela.retroworld.model.Results;
import com.pinguela.retroworld.model.Videojuego;
import com.pinguela.retroworld.service.impl.VideojuegoServiceImpl;
import com.pinguela.retroworld.util.DateUtils;

public class VideojuegoServiceTest {
	private static Logger logger = LogManager.getLogger(VideojuegoServiceTest.class);
	private VideojuegoService videojuegoService = null;
	
	public VideojuegoServiceTest() {
		videojuegoService = new VideojuegoServiceImpl();
	}
	
	public void testCreateCabecera() throws Exception{
		logger.traceEntry("Testing create datos cabecera...");
		Videojuego v = new Videojuego();
		v.setNombre("Persona 4");
		v.setFechaLanzamiento(DateUtils.getDate(2008, 07, 10));
		v.setIdDesarrolladora(13);
		v.setDescripcion("videojuego de rol desarrollado y publicado por Atlus para la consola de PlayStation 2, y cronológicamente es la quinta entrega dentro de la serie de Persona");
		videojuegoService.create(v);
		logger.info("Videojuego creado correctamente");
	}
	
	public void testCreateCompleto() throws Exception{
		logger.traceEntry("Testing create datos completos...");
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
		logger.info("Videojuego creado correctamente");
	}
	
	public void testUpdate() throws Exception{
		logger.traceEntry("Testing update...");
		Long id = 3l;
		Videojuego v = videojuegoService.findById(id);
		v.setIdDesarrolladora(2);
		if(!videojuegoService.update(v)) {
			logger.info("Error al actualizar el videojuego con id "+id);
		} else {
			logger.info("Videojuego con id "+id+" actualizado correctamente");
		}
	}
	
	public void testFindById() throws Exception{
		logger.traceEntry("Testing findById...");
		Long id = 2l;
		Videojuego v = videojuegoService.findById(id);
		if(v!=null) {
			logger.info("Videojuego con id "+id+" encontrado: ");
			logger.info(v);
		} else {
			logger.info("Error al encontrar el videojuego con id "+id);
		}
	}
	
	public void testFindByNombre() throws Exception{
		logger.traceEntry("Testing findByNombre...");
		VideojuegoCriteria criteria = new VideojuegoCriteria();
		criteria.setNombre("Zelda");
		Results <Videojuego> videojuegos = videojuegoService.findBy(criteria, 1, 200);
		if(videojuegos.getPage().isEmpty()) {
			logger.info("No se han encontrado videojuegos con el nombre "+criteria.getNombre());
		} else {
			logger.info("Videojuegos encontrados: "+videojuegos.getTotal());
			for(Videojuego v:videojuegos.getPage()) {
				logger.info(v);
			}
		}
	}
	
	public void testFindByGenero() throws Exception{
		logger.traceEntry("Testing findByGénero...");
		VideojuegoCriteria criteria = new VideojuegoCriteria();
		criteria.setIdGenero(1);
		Results <Videojuego> videojuegos = videojuegoService.findBy(criteria, 1, 200);
		if(videojuegos.getPage().isEmpty()) {
			logger.info("No se han encontrado videojuegos con el genero con id "+criteria.getIdGenero());
		} else {
			logger.info("Videojuegos encontrados: "+videojuegos.getTotal());
			for(Videojuego v:videojuegos.getPage()) {
				logger.info(v);
			}
		}
	}
	
	public void testFindByIdioma() throws Exception{
		logger.traceEntry("Testing findByIdioma...");
		VideojuegoCriteria criteria = new VideojuegoCriteria();
		criteria.setIdIdioma(3);
		Results <Videojuego> videojuegos = videojuegoService.findBy(criteria, 1, 200);
		if(videojuegos.getPage().isEmpty()) {
			logger.info("No se han encontrado videojuegos con el idioma con id "+criteria.getIdIdioma());
		} else {
			logger.info("Videojuegos encontrados: "+videojuegos.getTotal());
			for(Videojuego v:videojuegos.getPage()) {
				logger.info(v);
			}
		}
	}
	
	public void testFindByDesarrolladora() throws Exception{
		logger.traceEntry("Testing findByDesarrolladora...");
		VideojuegoCriteria criteria = new VideojuegoCriteria();
		criteria.setIdDesarrolladora(4);
		Results <Videojuego> videojuegos = videojuegoService.findBy(criteria, 1, 200);
		if(videojuegos.getPage().isEmpty()) {
			logger.info("No se han encontrado videojuegos con la desarrolladora con id "+criteria.getIdDesarrolladora());
		} else {
			logger.info("Videojuegos encontrados: "+videojuegos.getTotal());
			for(Videojuego v:videojuegos.getPage()) {
				logger.info(v);
			}
		}
	}
	
	public void testFindByPlataforma() throws Exception{
		logger.traceEntry("Testing findByPlataforma...");
		VideojuegoCriteria criteria = new VideojuegoCriteria();
		criteria.setIdPlataforma(5);
		Results <Videojuego> videojuegos = videojuegoService.findBy(criteria, 1, 200);
		if(videojuegos.getPage().isEmpty()) {
			logger.info("No se han encontrado videojuegos con la plataforma con id "+criteria.getIdPlataforma());
		} else {
			logger.info("Videojuegos encontrados: "+videojuegos.getTotal());
			for(Videojuego v:videojuegos.getPage()) {
				logger.info(v);
			}
		}
	}
	
	public void testFindByFechaLanzamiento() throws Exception{
		logger.traceEntry("Testing findByFechaLanzamiento...");
		VideojuegoCriteria criteria = new VideojuegoCriteria();
		criteria.setFechaLanzamientoDesde(DateUtils.getDate(2000, 01, 01));
		criteria.setFechaLanzamientoHasta(DateUtils.getDate(2005, 12, 31));;
		Results <Videojuego> videojuegos = videojuegoService.findBy(criteria, 1, 200);
		if(videojuegos.getPage().isEmpty()) {
			logger.info("No se han encontrado videojuegos en las fechas desde "+criteria.getFechaLanzamientoDesde()+" hasta "+criteria.getFechaLanzamientoHasta());
		} else {
			logger.info("Videojuegos encontrados: "+videojuegos.getTotal());
			for(Videojuego v:videojuegos.getPage()) {
				logger.info(v);
			}
		}
	}
	
	public void testFindByMultipleParameters() throws Exception{
		logger.traceEntry("Testing findByMultipleParameters...");
		VideojuegoCriteria criteria = new VideojuegoCriteria();
		criteria.setIdIdioma(3);
		criteria.setIdPlataforma(5);
		criteria.setFechaLanzamientoDesde(DateUtils.getDate(2000, 01, 01));
		criteria.setFechaLanzamientoHasta(new Date());
		Results <Videojuego> videojuegos = videojuegoService.findBy(criteria, 1, 200);
		if(videojuegos.getPage().isEmpty()) {
			logger.info("No se han encontrado videojuegos");
		} else {
			logger.info("Videojuegos encontrados: "+videojuegos.getTotal());
			for(Videojuego v:videojuegos.getPage()) {
				logger.info(v);
			}
		}
	}
	
	public void testFindByAll() throws Exception{
		logger.traceEntry("Testing findByAll...");
		VideojuegoCriteria criteria = new VideojuegoCriteria();
		Results <Videojuego> videojuegos = videojuegoService.findBy(criteria, 1, 200);
		if(videojuegos.getPage().isEmpty()) {
			logger.info("No se han encontrado videojuegos con la plataforma con id "+criteria.getIdPlataforma());
		} else {
			logger.info("Videojuegos encontrados: "+videojuegos.getTotal());
			for(Videojuego v:videojuegos.getPage()) {
				logger.info(v);
			}
		}
	}
	
	public static void main(String[]args) throws Exception{
		VideojuegoServiceTest test = new VideojuegoServiceTest();
		test.testCreateCabecera();
		test.testCreateCompleto();
		test.testUpdate();
		test.testFindById();
		test.testFindByNombre();
		test.testFindByGenero();
		test.testFindByIdioma();
		test.testFindByDesarrolladora();
		test.testFindByPlataforma();
		test.testFindByFechaLanzamiento();
		test.testFindByMultipleParameters();
		test.testFindByAll();
	}
}