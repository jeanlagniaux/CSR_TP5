package org.inria.restlet.mta.backend;

import java.util.Random;

public class Zone {

	private int nbSardine;
	
	public Zone() {
		Random rand = new Random();
		this.nbSardine = rand.nextInt(6);
	}

	public int getNbSardine() {
		return nbSardine;
	}

	public void setNbSardine(int nbSardine) {
		this.nbSardine = nbSardine;
	}
	
	

}
