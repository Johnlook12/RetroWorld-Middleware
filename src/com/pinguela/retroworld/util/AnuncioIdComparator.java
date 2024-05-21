package com.pinguela.retroworld.util;

import java.util.Comparator;

import com.pinguela.retroworld.model.Anuncio;

public class AnuncioIdComparator implements Comparator<Anuncio>{

	public AnuncioIdComparator() {
		
	}
	
	public int compare(Anuncio one, Anuncio other) {
		if(one.getId()<other.getId()) {
			return -1;
		} else if(one.getId()==other.getId()) {
			return 0;
		} else {
			return 1;
		}
	}
}
