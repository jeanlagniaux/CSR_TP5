package org.inria.restlet.mta.database.api.impl;

import java.util.Random;

import org.inria.restlet.mta.backend.PoissonPilote;
import org.inria.restlet.mta.backend.Requin;
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

	public Zone[][] getCarte() {
		return carte;
	}

	private void creaCarte() {
		for (int i = 0; i < carte.length; i++) {
			for (int j = 0; j < carte[i].length; j++) {
				carte[i][j] = new Zone(i, j, this);
				createPoissonPilote(carte[i][j]);
			}
		}
		System.out.println("il y a " + getNbRequin() + " requins dans l'océan");
	}

	@Override
	public int getNbRequin() {
		int cptReq = 0;
		for (int i = 0; i < carte.length; i++) {
			for (int j = 0; j < carte[i].length; j++) {
				if (getzoneByCoor(i, j).getHasRequin()) {
					cptReq++;
				}
			}
		}
		return cptReq;

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

	@Override
	public int getNbPoissonPilote() {
		int cptPp = 0;
		for (int i = 0; i < carte.length; i++) {
			for (int j = 0; j < carte[i].length; j++) {
				if (getzoneByCoor(i, j).getHasRequin()) {
					cptPp++;
				}
			}
		}
		return cptPp;

	}

	@Override
	public boolean hasPoisonPilote() {
		boolean hasPp = false;
		for (int i = 0; i < carte.length; i++) {
			for (int j = 0; j < carte[i].length; j++) {
				if (carte[i][j].getHasRequin()) {
					hasPp = true;
				}
			}
		}
		return hasPp;
	}

	@Override
	public synchronized void deplacementReq(Requin req) {
		for (int i = 0; i < carte.length; i++) {
			for (int j = 0; j < carte[i].length; j++) {
				if (carte[i][j].getHasRequin()) {
					System.out.println(
							"Requin dans la zone : (" + i + ")(" + j + ") ?  ->" + getzoneByCoor(i, j).getHasRequin()
									+ " --> Nombre de sardines : " + getzoneByCoor(i, j).getNbSardine());
				}
			}
		}

		int dep_x = req.getZone().getCoordX();
		int dep_y = req.getZone().getCoordY();

		int arr_x = dep_x;
		int arr_y = dep_y;

		Random rand = new Random();
		int r = rand.nextInt(4);

		if (r == 0) {
			System.out.println("le requin " + req.currentThread().getName() + " veut se déplace en bas");
			arr_x = (dep_x + 1) % (LONGUEUR);

		} else if (r == 1) {
			System.out.println("le requin " + req.currentThread().getName() + " veut se déplace en haut");
			if (dep_x == 0) {
				arr_x = 4;
			} else {
				arr_x = (dep_x - 1) % (LONGUEUR);
			}

		} else if (r == 2) {
			System.out.println("le requin " + req.currentThread().getName() + " veut se déplace à droite");
			arr_y = (dep_y + 1) % (LARGEUR);

		} else if (r == 3) {
			System.out.println("le requin " + req.currentThread().getName() + " veut se déplace à gauche");
			if (dep_y == 0) {
				arr_y = 4;
			} else {
				arr_y = (dep_y - 1) % (LARGEUR);
			}

		}

		// on a notre stock de poisson pilote
		// pp saccrocher
		while (req.getPlaceDispo() != 0) {
			if (getzoneByCoor(dep_x, dep_y).getListPps().isEmpty()) {
				break;
			}
			Random pois = new Random();
			int lepoois = pois.nextInt(getzoneByCoor(dep_x, dep_y).getListPps().size());
			if (lepoois == 0) {
				lepoois = lepoois + 1;
			}
			System.out.println("========================================================== size = " + lepoois);
			req.ppSaccrocher(getzoneByCoor(dep_x, dep_y).getListPps().get(lepoois));
		}

		getzoneByCoor(dep_x, dep_y).setHasRequin(false);

		while (getzoneByCoor(arr_x, arr_y).getHasRequin()) {
			try {
				System.out.println("il y a un requin dans la zone(" + arr_x + ")(" + arr_y + ") -> on attend");
				wait(); // req.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Zone d'arrivée prévue : " + getzoneByCoor(arr_x, arr_y));

		// on change de zone

		req.setZone(getzoneByCoor(arr_x, arr_y));
		getzoneByCoor(arr_x, arr_y).setHasRequin(true);

		for (int i = 0; i < req.getMyPLs().size(); i++) {
			req.ppSeDecrocher(req.getMyPLs().get(i));
			i++;
		}

		System.out.println(
				"Le requin " + req.currentThread().getName() + " se trouve désormais dans la zone de coordonné : ("
						+ req.getZone().getCoordX() + ")(" + req.getZone().getCoordY() + ")");
		notifyAll();

	}

	public void createPoissonPilote(Zone zone) {
		Random rand = new Random();
		int x = rand.nextInt(2);
		if (x == 1) {
			int y = rand.nextInt(5) + 1;
			for (int i = 0; i < y; i++) {
				PoissonPilote p = new PoissonPilote(zone);
				zone.getListPps().add(p);
				i++;
			}
		}
	}

}
