package org.inria.restlet.mta.backend;

import java.util.Random;

import org.inria.restlet.mta.database.api.OceanV2;

public class ZoneV2 {

	private int nbSardine;
	private int coordX;
	private int coordY;
	private RequinV2 requin;
	private Boolean hasRequin;
	private OceanV2 ocean;

	public ZoneV2(int coordX, int coordY, OceanV2 ocean) {
		this.coordX = coordX;
		this.coordY = coordY;
		this.ocean = ocean;
		Random rand = new Random();
		this.nbSardine = rand.nextInt(6);
	}

	public OceanV2 getOcean() {
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

	public RequinV2 getRequin() {
		return requin;
	}

	public void setRequin(RequinV2 requin) {
		this.requin = requin;
	}
	
}
