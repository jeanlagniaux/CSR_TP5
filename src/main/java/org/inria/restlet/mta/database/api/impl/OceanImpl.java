package org.inria.restlet.mta.database.api.impl;

import java.util.Random;

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

	public Zone getzoneByCoor(int x, int y) {
		return carte[x][y];
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
		int dep_x = zone.getCoordX();
		int dep_y = zone.getCoordY();

		int arr_x = dep_x;
		int arr_y = dep_y;

		Random rand = new Random();
		int r = rand.nextInt(4);

		if (r == 0) {
			arr_x = dep_x + 1 % LONGUEUR;

		} else if (r == 1) {
			arr_x = dep_x - 1 % LONGUEUR;

		} else if (r == 2) {
			arr_y = dep_y + 1 % LARGEUR;

		} else if (r == 3) {
			arr_y = dep_y - 1 % LARGEUR;
		}
		
		while (getzoneByCoor(arr_x, arr_y).getHasRequin()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// on choisi la prochaine zone random qu'on appel new zone
		zone.getRequin().setZone(getzoneByCoor(arr_x, arr_y));
		notifyAll();
	}

	public static void main(String args[]) {
	}

}
