package org.inria.restlet.mta.backend;

import java.util.ArrayList;
import java.util.Random;

import org.inria.restlet.mta.database.api.Ocean;

public class Zone {

	private int nbSardine;
	private int coordX;
	private int coordY;
	private Requin requin;
	private Boolean hasRequin;
	private Ocean ocean;
	private ArrayList<PoissonPilote> listPps = new ArrayList<PoissonPilote>();

	public Zone(int coordX, int coordY, Ocean ocean) {
		this.coordX = coordX;
		this.coordY = coordY;
		this.ocean = ocean;
		createRequin();
		createPoissonPilote();
		Random rand = new Random();
		this.nbSardine = rand.nextInt(10);
	}

	public Ocean getOcean() {
		return ocean;
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
	
	public Boolean listPoissonPiloteIsEmpty() {
		if(listPps.isEmpty()) {
			return true;
		}
		return false;
	}

	public void createRequin() {
		this.hasRequin = false;
		Random rand = new Random();
		int x = rand.nextInt(3);
		if (x == 1) {
			this.requin = new Requin(this);
			this.hasRequin = true;
		}
	}
	
	public void createPoissonPilote() {
		Random rand = new Random();
		int x = rand.nextInt(2);
		if (x == 1) {
			int y = rand.nextInt(5) + 1;
			for (int i = 0; i < y ; i++) {
				PoissonPilote p = new PoissonPilote(this);
				listPps.add(p);
				i++;
			}
		}
	}

}
