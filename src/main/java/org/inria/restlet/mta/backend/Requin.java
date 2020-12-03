package org.inria.restlet.mta.backend;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.inria.restlet.mta.database.api.Ocean;

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
		System.out.println("le requin " + Thread.currentThread().getName() + " Nage dans l'ocean");
		manger();
		seDeplacer();
	}

	public synchronized void seDeplacer() {
		System.out.println("Le requin " + Thread.currentThread().getName() + " se déplace");
		while (getLifeRemaining() != 0) {
			setLifeRemaining(getLifeRemaining() - 1);
			System.out.println("Il reste " + getLifeRemaining() + " cycles de vie au requin");
			getZone().getOcean().deplacement(this.zone);
		}
		System.out.println("Le requin a fini de chasser dans cette région de l'océan");
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
