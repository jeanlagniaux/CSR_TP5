package org.inria.restlet.mta.backend;

public class PoissonPilote extends Thread {

	private static final int lifeMax = 3;
	private Zone zone;
	private int lifeRemaining;
	private Boolean hasRequin;
	private Requin req = null;

	public PoissonPilote(Zone zone) {
		this.zone = zone;
		this.lifeRemaining = lifeMax;
		this.hasRequin = false;
	}

	public void run() {
		System.out.println("le poison pilote" + Thread.currentThread().getName() + " commence sa vie");
		while (this.getLifeRemaining() != 0) {
			nager();
		}
	}

	public synchronized void nager() {
		Zone zoneDep = this.getZone();
		while (getLifeRemaining() != 0) {
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("je suis le poisson " + Thread.currentThread().getName() + "et il me reste "
					+ getLifeRemaining() + " de jour a vivre");

			saccrocher();
			while (this.getZone().equals(this.getReq().getZone())) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			decrocher();

			setLifeRemaining(getLifeRemaining() - 1);
		}

	}

	public synchronized void saccrocher() {
		while (true) {
			if (this.zone.getHasRequin() && this.zone.getRequin().getPlaceDispo() != 0) {
				this.getZone().getOcean().ppSaccrocher(this.zone, this);
				notifyAll();
			} else {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public synchronized void decrocher() {
		this.getZone().getOcean().ppSeDecrocher(this);
		notifyAll();
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

	public Requin getReq() {
		return req;
	}

	public void setReq(Requin req) {
		this.req = req;
	}

}
