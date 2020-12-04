package org.inria.restlet.mta.application;

import org.inria.restlet.mta.backend.Requin;
import org.inria.restlet.mta.backend.Zone;
import org.inria.restlet.mta.database.api.Ocean;
import org.inria.restlet.mta.database.api.impl.OceanImpl;

class OceanSysteme {
	

	Ocean ocean;
	public OceanSysteme() {
		ocean = new OceanImpl();
		
		for (int i = 0; i < ocean.getCarte().length; i++) {
			for (int j = 0; j < ocean.getCarte().length; j++) {
				if (ocean.getzoneByCoor(i, j).getHasRequin()) {
					ocean.getzoneByCoor(i, j).getRequin().start();
				}
				
			}
		}
	}

	/* Point d'entree du programme */

	

	public static void main(String[] args) {
		new OceanSysteme();
	}

} // class SystemeEmprunt
