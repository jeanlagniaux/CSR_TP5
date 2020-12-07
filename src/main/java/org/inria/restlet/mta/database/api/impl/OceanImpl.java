package org.inria.restlet.mta.database.api.impl;

import java.util.Random;

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

	private void creaCarte() {
		for (int i = 0; i < carte.length; i++) {
			for (int j = 0; j < carte[i].length; j++) {
				carte[i][j] = new Zone(i, j, this);
			}
		}
		System.out.println("il y a " + getNbRequin() + " requins dans l'océan");
	}

	@Override
	public int getNbRequin() {
		int cpt = 0;
		for (int i = 0; i < carte.length; i++) {
			for (int j = 0; j < carte[i].length; j++) {
				if (getzoneByCoor(i, j).getHasRequin()) {
					cpt++;
				}
			}
		}
		return cpt;

	}

	public Zone[][] getCarte() {
		return carte;
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
	public synchronized void deplacement(Zone zone) {
		
		for (int i = 0; i < carte.length; i++) {
			for (int j = 0; j < carte[i].length; j++) {
				System.out.println(
						"Requin dans la zone : (" + i + ")(" + j + ") ?  ->" + getzoneByCoor(i, j).getHasRequin()+" ->"+getzoneByCoor(i, j));
			}
		}
		
		int dep_x = zone.getCoordX();
		int dep_y = zone.getCoordY();

		int arr_x = dep_x;
		int arr_y = dep_y;

		Random rand = new Random();
		int r = rand.nextInt(4);

		if (r == 0) {
			System.out.println("le requin "+zone.getRequin().currentThread().getName()+" veut se déplace en bas");
			arr_x = (dep_x + 1) % (LONGUEUR);
			

		} else if (r == 1) {
			System.out.println("le requin "+zone.getRequin().currentThread().getName()+" veut se déplace en haut");
			if(dep_x == 0){
				arr_x = 4;
			} else {
				arr_x = (dep_x - 1) % (LONGUEUR);
			}
			
			
		} else if (r == 2) {
			System.out.println("le requin "+zone.getRequin().currentThread().getName()+" veut se déplace à droite");
			arr_y = (dep_y + 1) % (LARGEUR);
			

		} else if (r == 3) {
			System.out.println("le requin "+zone.getRequin().currentThread().getName()+" veut se déplace à gauche");
			if(dep_y == 0){
				arr_y = 4;
			} else {
				arr_y = (dep_y - 1) % (LARGEUR);
			}
			
			
		}

		while (getzoneByCoor(arr_x, arr_y).getHasRequin()) {
			try {
				System.out.println("il y a un requin dans la zone("+arr_x+")("+arr_y+") -> on attend");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Zone d'arrivée prévue : "+getzoneByCoor(arr_x, arr_y));
		getzoneByCoor(dep_x, dep_y).setHasRequin(false);
		zone.getRequin().setZone(getzoneByCoor(arr_x, arr_y));
		getzoneByCoor(arr_x, arr_y).setHasRequin(true);
		
		System.out.println("Le requin "+zone.getRequin().currentThread().getName()+" se trouve désormais dans la zone de coordonné : ("+zone.getRequin().getZone().getCoordX()+")("+zone.getRequin().getZone().getCoordY()+")");
		System.out.println("Zone du requin "+zone.getRequin().currentThread().getName()+" : "+ zone.getRequin().getZone());
		notify();
		
	}

}
