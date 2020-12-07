package org.inria.restlet.mta.backend;

public class PoissonPilote extends Thread{

	private static final int lifeMax = 3;
	private Zone zone;
	private int lifeRemaining;
	private Boolean hasRequin;
	
	public PoissonPilote(Zone zone) {
		this.zone = zone;
		this.lifeRemaining = lifeMax;
		this.hasRequin = false;
	}

	public void run() {
		
	}
	
	public synchronized void nager() {
		
		// ici mettre en attente ? 
		
		while (getLifeRemaining() != 0) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setLifeRemaining(getLifeRemaining() - 1);
		}
		
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

	public Boolean getHasRequin() {
		return hasRequin;
	}

	public void setHasRequin(Boolean hasRequin) {
		this.hasRequin = hasRequin;
	}

}
