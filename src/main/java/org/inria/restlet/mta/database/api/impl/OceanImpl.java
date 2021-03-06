package org.inria.restlet.mta.database.api.impl;

import java.util.Iterator;
import java.util.Random;

import org.inria.restlet.mta.backend.PoissonPilote;
import org.inria.restlet.mta.backend.Requin;
import org.inria.restlet.mta.backend.Zone;
import org.inria.restlet.mta.database.api.Ocean;
import org.inria.restlet.mta.resources.requinsRessource;

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

	/**
	 * Creation de notre ocean avec l'initialisation des zones et l'ajout de
	 * l'identifiant requin si il y a un requin dans la zone cr��e
	 */
	private void creaCarte() {
		int cpt = 0;
		int cptPois = 0;
		for (int i = 0; i < carte.length; i++) {
			for (int j = 0; j < carte[i].length; j++) {
				carte[i][j] = new Zone(i, j, this);
				if (carte[i][j].getHasRequin()) {
					cpt = cpt + 1;
					carte[i][j].getRequin().setIdRequin(cpt);
				}
				if (!carte[i][j].getListPps().isEmpty()) {
					cptPois = cptPois + carte[i][j].getListPps().size();
				}
			}
		}

		System.out.println("il y a " + cptPois + " pp dans l'oc�an");
		System.out.println("il y a " + getNbRequin() + " requins dans l'oc�an");
	}

	public Zone getzoneByCoor(int x, int y) {
		return carte[x][y];
	}

	public Zone[][] getCarte() {
		return carte;
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
	public synchronized Zone choixZoneReq(Requin req) {
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
		int r = rand.nextInt(4); // utilisation d'un random afin de d�finir aleatoirement la zone vers laquelle
									// il va se diriger

		if (r == 0) {
			System.out.println("le requin " + req.currentThread().getName() + " veut se d�place en bas");
			arr_x = (dep_x + 1) % (LONGUEUR);

		} else if (r == 1) {
			System.out.println("le requin " + req.currentThread().getName() + " veut se d�place en haut");
			if (dep_x == 0) {
				arr_x = 4;
			} else {
				arr_x = (dep_x - 1) % (LONGUEUR);
			}

		} else if (r == 2) {
			System.out.println("le requin " + req.currentThread().getName() + " veut se d�place � droite");
			arr_y = (dep_y + 1) % (LARGEUR);

		} else if (r == 3) {
			System.out.println("le requin " + req.currentThread().getName() + " veut se d�place � gauche");
			if (dep_y == 0) {
				arr_y = 4;
			} else {
				arr_y = (dep_y - 1) % (LARGEUR);
			}

		}
		notifyAll();
		return (getzoneByCoor(arr_x, arr_y));

	}

	@Override
	public synchronized void deplacementReq(Requin req, Zone zoneArr) {

		req.getZone().setHasRequin(false);

		while (zoneArr.getHasRequin()) {
			try {
				System.out.println("il y a un requin dans la zone(" + zoneArr.getCoordX() + ")(" + zoneArr.getCoordY()
						+ ") -> on attend");
				wait(); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Zone d'arriv�e pr�vue : " + zoneArr);
		req.setZone(zoneArr);
		zoneArr.setHasRequin(true);
		System.out.println(
				"Le requin " + req.currentThread().getName() + " se trouve d�sormais dans la zone de coordonn� : ("
						+ req.getZone().getCoordX() + ")(" + req.getZone().getCoordY() + ")");
		notifyAll();
	}

	public synchronized void ppSaccrocher(Zone zone, PoissonPilote pp) {
		zone.getRequin().getMyPLs().add(pp);
		pp.setReq(zone.getRequin());
		zone.getListPps().remove(pp);
		zone.getRequin().setPlaceDispo(zone.getRequin().getPlaceDispo() - 1);
		notifyAll();
	}

	public synchronized void ppSeDecrocher(PoissonPilote pp) {
		pp.setZone(pp.getReq().getZone());
		pp.getReq().getMyPLs().remove(pp);
		System.out.println("le poison " + Thread.currentThread().getName() + " vient d'arriver dans la zone ");
	}

	@Override
	public int getNbSardine() {
		int cptSardine = 0;
		for (int i = 0; i < carte.length; i++) {
			for (int j = 0; j < carte[i].length; j++) {
				cptSardine = cptSardine + getzoneByCoor(i, j).getNbSardine();

			}
		}
		return cptSardine;
	}

	@Override
	public Requin createRequin(Zone zone) {
		return zone.creaRequin();

	}

	@Override
	public Requin getRequinId(int id) {
		Requin reqRep = null;
		for (int i = 0; i < carte.length; i++) {
			for (int j = 0; j < carte[i].length; j++) {
				if (getzoneByCoor(i, j).getHasRequin()) {
					Zone zoneAvReq = getzoneByCoor(i, j);
					if (zoneAvReq.getRequin().getIdRequin() == id) {
						return reqRep = getzoneByCoor(i, j).getRequin();
					}
				}

			}
		}
		return reqRep;
	}

	@Override
	public boolean existReqId(int id) {
		if (getRequinId(id).equals(null)) {
			return false;
		} else {
			return true;
		}

	}

	public Requin createRequinId(Zone zone, int id) {
		return zone.creaRequinId(id);
	}

}
