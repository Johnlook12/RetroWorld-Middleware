package com.pinguela.retroworld.util;

import java.util.Comparator;

import com.pinguela.retroworld.model.Anuncio;

public class AnuncioPrecioComparator implements Comparator<Anuncio>{
	
	private boolean asc = true;
	
	public AnuncioPrecioComparator() {
		
	}
	
	public AnuncioPrecioComparator(boolean asc) {
		setAsc(asc);
	}
	
	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public int compare(Anuncio one, Anuncio other) {
		if(one.getPrecio()<other.getPrecio()) {
			return asc?-1:1;
		} else if(one.getPrecio()==other.getPrecio()) {
			return 0;
		} else {
			return asc?1:-1;
		}
	}
}
