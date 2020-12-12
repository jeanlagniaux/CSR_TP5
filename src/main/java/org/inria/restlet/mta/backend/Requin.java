package org.inria.restlet.mta.backend;

import java.util.ArrayList;

public class Requin extends Thread {

	private static final int lifeMax = 10;
	private static final int placeMax = 3;
	private int lifeRemaining;
	private int placeDispo;
	private Zone zone;
	private ArrayList<PoissonPilote> myPLs = new ArrayList<PoissonPilote>();

	public Requin(Zone zone) {
		this.zone = zone;
		this.lifeRemaining = lifeMax;
		this.placeDispo = placeMax;
	}

	public void run() {
		System.out.println("le requin " + Thread.currentThread().getName() + " Nage dans la zone : ("
				+ this.zone.getCoordX() + ")(" + this.zone.getCoordY() + ")");
		nager();
	}

	public synchronized void nager() {

		while (getLifeRemaining() != 0) {
			manger();
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// on essaye d'accorcher un pp
			getZone().getOcean().deplacementReq(this);
			setLifeRemaining(getLifeRemaining() - 1);
		}
		manger(); // pour manger dans la derniere zone;
		getZone().setHasRequin(false);
		getZone().setRequin(null);
		System.out.println(
				"Le requin " + Thread.currentThread().getName() + " a fini de chasser dans cette région de l'océan");

	}

	public void manger() {
		if (this.zone.getNbSardine() > 0) {
			this.zone.setNbSardine(this.zone.getNbSardine() - 1);
		}
	}

	public synchronized void ppSaccrocher(PoissonPilote pp) {
		this.getZone().getListPps().remove(pp);
		this.getMyPLs().add(pp);
		this.setPlaceDispo(this.getPlaceDispo() - 1);
		System.out.println("");
		notifyAll();

	}

	public synchronized void ppSeDecrocher(PoissonPilote pp) {
		pp.setZone(this.getZone());
		this.getMyPLs().remove(pp);
		this.getZone().getListPps().add(pp);
		System.out.println("le poison " + Thread.currentThread().getName() + " vient d'arriver dans la zone ");
		
	}

	// GETTER AND SETTER

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public int getLifeRemaining() {
		return lifeRemaining;
	}

	public void setLifeRemaining(int lifeRemaining) {
		this.lifeRemaining = lifeRemaining;
	}

	public int getPlaceDispo() {
		return placeDispo;
	}

	public void setPlaceDispo(int placeDispo) {
		this.placeDispo = placeDispo;
	}

	public ArrayList<PoissonPilote> getMyPLs() {
		return myPLs;
	}

}
