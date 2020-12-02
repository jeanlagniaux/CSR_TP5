package org.inria.restlet.mta.database.api.impl;

import org.inria.restlet.mta.backend.Zone;
import org.inria.restlet.mta.database.api.Ocean;

/**
 *
 * In-memory database
 *
 * @author ctedeschi
 * @author msimonin
 *
 */
public class OceanImpl implements Ocean {

	static final int LONGUEUR = 5;
	static final int LARGEUR = 5;

	private Zone[][] carte = new Zone[LONGUEUR][LARGEUR];
	
	public OceanImpl() {
		creaCarte();
	}

	private void creaCarte() {
		for (int i = 0; i < carte.length; i++) {
			for (int j = 0; j < carte[i].length; j++) {
				new Zone(i, j);
			}
		}
	}

	@Override
	public boolean hasRequin() {
		boolean hasRequin = false;
		for (int i = 0; i < carte.length; i++) {
			for (int j = 0; j < carte[i].length; j++) {
				if (carte[i][j].getHasRequin()) {
					hasRequin = true;
				}
			}
		}
		return hasRequin;
	}
	
	public void deplacement(Zone zone) {
		
	}

	public static void main(String args[]) {
		
		OceanImpl oc = new OceanImpl();
		
		
	}
	

}
