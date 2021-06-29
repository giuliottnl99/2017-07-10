package it.polito.tdp.artsmia.model;

import java.util.List;

public class Percorso {
	List<ArtObject> oggettiScelti;
	int lungh;
	
	public Percorso(List<ArtObject> oggettiScelti, int lungh) {
		super();
		this.oggettiScelti = oggettiScelti;
		this.lungh = lungh;
	}
	public Percorso() {
		
	}
	
}
