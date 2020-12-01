package org.inria.restlet.mta.database.api.impl;

import java.util.HashMap;

import org.inria.restlet.mta.backend.Zone;
import org.inria.restlet.mta.database.api.Ocean;
import org.inria.restlet.mta.internals.User;

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

	//private Zone zone;

	private int[][] carte = new int[LONGUEUR][LARGEUR];

	public OceanImpl() {
		creaCarte();
	}

	private void creaCarte() {
		for (int i = 0; i < carte.length; i++) {
			for (int j = 0; j < carte[i].length; j++) {
				new Zone(i,j);
			}
		}
	}

	/**
	 *
	 * Synchronized user creation.
	 * 
	 * @param name
	 * @param age
	 *
	 * @return the user created
	 */

	@Override
	public void seDeplacer() {
		
	}

}
