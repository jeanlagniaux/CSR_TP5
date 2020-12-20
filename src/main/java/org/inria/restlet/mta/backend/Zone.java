package org.inria.restlet.mta.backend;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.LayoutStyle.ComponentPlacement;

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
		createPoissonPilote();
		createRequinAlea();
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

	/**
	 * ici on utilise des random pour savoir si oui ou non on crée un requin dans la
	 * zone (chance de 1/3)
	 */
	public void createRequinAlea() {
		this.hasRequin = false;
		Random rand = new Random();
		int x = rand.nextInt(3);
		if (x == 1) {
			this.requin = new Requin(this);
			this.hasRequin = true;
		}
	}

	public Requin creaRequin() {
		if (this.getHasRequin() == false) {
			this.requin = new Requin(this);
			this.hasRequin = true;
		}
		return this.requin;
	}

	public Requin creaRequinId(int id) {
		if (this.getHasRequin() == false) {
			this.requin = new Requin(this, id);
			this.hasRequin = true;
		}
		return this.requin;
	}

	/**
	 * ici on utilise des random pour savoir si oui ou non on crée des poissons
	 * pilotes dans cette zone et ensuite on défini aléatoirement le nombre de
	 * poissons pilotes que l'on crée
	 */
	public void createPoissonPilote() {
		int ppNb = 0;
		Random rand = new Random();
		int x = rand.nextInt(2);
		if (x == 1) {
			int y = rand.nextInt(5) + 1;
			for (int i = 0; i < y; i++) {
				ppNb = ppNb + 1;
				PoissonPilote p = new PoissonPilote(this);
				listPps.add(p);
			}
		}
	}

	public ArrayList<PoissonPilote> getListPps() {
		return listPps;
	}

	public void setListPps(ArrayList<PoissonPilote> listPps) {
		this.listPps = listPps;
	}

}
