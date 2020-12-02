package org.inria.restlet.mta.application;

import java.util.Random;

class OceanSysteme {

	/* Constantes */

	static final int NB_SITES = 5;
	static final int MAX_CLIENTS = 10;

	/* Attributs */

	private Ocean ocean = new Ocean();
	private Client[] clients = new Client[MAX_CLIENTS];
	private Camion camion = null;
	private int nbClients = 0;

	/* Constructeur du systeme d'emprunt */
	OceanSysteme() {

		/* Instanciation des sites */
		for (int i = 0; i < NB_SITES; i++) {
			sites[i] = new Site(i);
		}
		System.out.println("les " + sites.length + " sites ont été crées");

		Random r = new Random();
		/* Instanciation des clients */
		for (int i = 0; i < MAX_CLIENTS; i++) {
			int siteDepId = r.nextInt(NB_SITES); // ajout de + 1 pour avoir le dernier site
			int siteArrId = r.nextInt(NB_SITES);
			clients[i] = new Client(sites[siteDepId], sites[siteArrId], i);
			clients[i].start();
		}
		/* Instanciation du camion */
		camion = new Camion(sites, 10);
		camion.start();

	}

	/* Point d'entree du programme */

	public static void main(String[] args) {

		new OceanSysteme();

	}

} // class SystemeEmprunt
