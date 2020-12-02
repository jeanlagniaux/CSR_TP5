package org.inria.restlet.mta.backend;

import java.util.Random;

public class Zone {

	private int nbSardine;
	private int coordX;
	private int coordY;
	private Requin requin;
	private Boolean hasRequin;
	
	public Zone(int coordX, int coordY) {
		this.coordX = coordX;
		this.coordY = coordY;
		createRequin();
		Random rand = new Random();
		this.nbSardine = rand.nextInt(6);
	}

	public int getNbSardine() {
		return nbSardine;
	}

	public void setNbSardine(int nbSardine) {
		this.nbSardine = nbSardine;
	}
	
	public Boolean getHasRequin() {
		return hasRequin;
	}

	public void setHasRequin(Boolean hasRequin) {
		this.hasRequin = hasRequin;
	}	
	
	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	public Requin getRequin() {
		return requin;
	}

	public void setRequin(Requin requin) {
		this.requin = requin;
	}

	public void createRequin(){
		this.hasRequin = false;
		Random rand = new Random();
		int x = rand.nextInt(6);
		if( x == 1){
			this.requin = new Requin(this);
			this.hasRequin = true;
		}
	}

}
