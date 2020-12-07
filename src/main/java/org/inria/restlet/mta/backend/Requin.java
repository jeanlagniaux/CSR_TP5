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
			getZone().getOcean().deplacement(this);
			setLifeRemaining(getLifeRemaining() - 1);
		}
		manger(); // pour manger dans la derniere zone;
		getZone().setHasRequin(false);
		getZone().setRequin(null);
		System.out.println("Le requin " + Thread.currentThread().getName() + " a fini de chasser dans cette région de l'océan");
		
	} 
	

	public int getLifeRemaining() {
		return lifeRemaining;
	}

	public void setLifeRemaining(int lifeRemaining) {
		this.lifeRemaining = lifeRemaining;
	}

	public void manger() {
		if(this.zone.getNbSardine()>0) {
			this.zone.setNbSardine(this.zone.getNbSardine() - 1);
		}
	}
}
