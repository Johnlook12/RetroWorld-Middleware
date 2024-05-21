package com.pinguela.retroworld.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.pinguela.retroworld.model.Anuncio;
import com.pinguela.retroworld.util.AnuncioPrecioComparator;

public class OrdenarHelloWorld {
	
	
	public static void main(String[]args) {
		List<Anuncio> anuncios = new ArrayList<Anuncio>();
		Anuncio a;
		a = new Anuncio();
		a.setId(3l);
		a.setTitulo("Castlevania a buen precio");
		a.setPrecio(20.0d);
		anuncios.add(a);
		
		a = new Anuncio();
		a.setId(2l);
		a.setTitulo("The witcher 3 ofertón!!");
		a.setPrecio(15.0d);
		anuncios.add(a);
		
		a = new Anuncio();
		a.setId(1l);
		a.setTitulo("Super mario bros 3 descuento");
		a.setPrecio(30.0d);		anuncios.add(a);
		
		for(Anuncio anuncio:anuncios) {
			System.out.println(anuncio);
		}
		Collections.sort(anuncios, new AnuncioPrecioComparator());
		System.out.println("Después de sort");
		for(Anuncio anuncio:anuncios) {
			System.out.println(anuncio);
		}
	}
}
