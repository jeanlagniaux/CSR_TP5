package org.inria.restlet.mta.application;

import java.util.Iterator;

import org.inria.restlet.mta.backend.PoissonPilote;
import org.inria.restlet.mta.database.api.Ocean;
import org.inria.restlet.mta.database.api.impl.OceanImpl;

class OceanSysteme {

	Ocean ocean;

	public OceanSysteme() {
		ocean = new OceanImpl();
		creationPoissonPilote(ocean);
		creationRequin(ocean);

	}

	private void creationPoissonPilote(Ocean ocean2) {
		for (int i = 0; i < ocean2.getCarte().length; i++) {
			for (int j = 0; j < ocean2.getCarte().length; j++) {
				if (!ocean2.getzoneByCoor(i, j).getListPps().isEmpty()) {
					for (Iterator<PoissonPilote> iterator = ocean2.getzoneByCoor(i, j).getListPps().iterator(); iterator
							.hasNext();) {
						PoissonPilote pp = (PoissonPilote) iterator.next();
						pp.start();
					}
				}
			}
		}
	}

	private void creationRequin(Ocean ocean2) {
		for (int i = 0; i < ocean2.getCarte().length; i++) {
			for (int j = 0; j < ocean2.getCarte().length; j++) {
				if (ocean2.getzoneByCoor(i, j).getHasRequin()) {
					ocean2.getzoneByCoor(i, j).getRequin().start();
				}
			}
		}
	}

	public static void main(String[] args) {
		new OceanSysteme();
	}

}
