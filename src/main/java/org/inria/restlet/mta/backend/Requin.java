package org.inria.restlet.mta.backend;

public class Requin extends Thread {

	private static final int lifeMax = 10;
	private int lifeRemaining;

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	private Zone zone;

	public Requin(Zone zone) {
		this.zone = zone;
		this.lifeRemaining = lifeMax;
	}

	public void run() {
		System.out.println("le requin " + Thread.currentThread().getName() + " Nage dans la zone : ("+this.zone.getCoordX()+")("+this.zone.getCoordY()+")");
		manger();
		seDeplacer();
	}

	public synchronized void seDeplacer() {
		System.out.println("Le requin " + Thread.currentThread().getName() + " se déplace");
		
		while (getLifeRemaining() != 0) {
			//this.zone.setRequin(null);
			getZone().getOcean().deplacement(this.zone);
			//this.zone.setRequin(this);
			setLifeRemaining(getLifeRemaining() - 1);
			//System.out.println("Il reste " + getLifeRemaining() + " cycles de vie au requin " + Thread.currentThread().getName() );
		}
		
		System.out.println("Le requin " + Thread.currentThread().getName() + " a fini de chasser dans cette région de l'océan");
	} 
	

	public int getLifeRemaining() {
		return lifeRemaining;
	}

	public void setLifeRemaining(int lifeRemaining) {
		this.lifeRemaining = lifeRemaining;
	}

	public void manger() {
		this.zone.setNbSardine(this.zone.getNbSardine() - 1);
	}
}
