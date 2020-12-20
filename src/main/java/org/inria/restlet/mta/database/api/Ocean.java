package org.inria.restlet.mta.database.api;

import org.inria.restlet.mta.backend.PoissonPilote;
import org.inria.restlet.mta.backend.Requin;
import org.inria.restlet.mta.backend.Zone;

/**
 *
 * Interface to the database.
 *
 * @author msimonin
 *
 */
public interface Ocean {

	/**
	 * permet de savoir s'il y a encore un requin dans l'océan
	 * 
	 * @return un boolean qui indique si il reste un requin ou non
	 */
	public boolean hasRequin();

	/**
	 * permet de savoir s'il y a encore un poissonPilote dans l'océan
	 * 
	 * @return un boolean qui indique si il reste un poisson pilote ou non
	 */
	public boolean hasPoisonPilote();

	/**
	 * permet de retourner une zone précise à partir de ses coordonées
	 * 
	 * @param int x
	 * @param int y
	 * @return une zone
	 */
	public Zone getzoneByCoor(int x, int y);

	/**
	 * permet au requin de choisir la zone vers laquelle il va se déplacer de
	 * manière aléatoire
	 * 
	 * @param un requin pour pouvoir l'appeler dans le run du requin
	 * 
	 * @return la zone vers laquelle le requin va se déplacer
	 */
	public Zone choixZoneReq(Requin req);

	/**
	 * permet d'effectuer le déplacement du requin
	 * 
	 * @param un requin
	 * @param la zone d'arrivée qui aurait été obtenue grace à la méthode
	 *           ChoixZoneReq()
	 * 
	 * 
	 */
	public void deplacementReq(Requin req, Zone zoneArr);

	/**
	 * permet de retourner le nombre de requin dans l'océan
	 * 
	 * @return le nombre de requin dans l'océan
	 */
	int getNbRequin();

	/**
	 * permet de retourner la carte qui dans notre cas est l'océan
	 * 
	 * @return l'océan
	 */
	public Zone[][] getCarte();

	/**
	 * permet au poisson pilote de faire s'accrocher à un requin et rentrer dans la
	 * liste de poisson pilote du requin
	 */
	public void ppSaccrocher(Zone zone, PoissonPilote pp);

	/**
	 * permet au poisson pilote de faire se décrocher du requin et donc de sortir
	 * de la liste de ce requin et de prendre la nouvelle zone en paramètre
	 */
	public void ppSeDecrocher(PoissonPilote pp);

	public int getNbSardine();

	/**
	 * permet de créer un requin avec une zone
	 * 
	 * @param une zone
	 * @return un requin
	 */
	public Requin createRequin(Zone zone);

	/**
	 * permet de créer un requin avec une zone et un identifiant
	 * 
	 * @param une zone
	 * @param un int pour l'identifiant
	 * @return un requin
	 */
	public Requin createRequinId(Zone zone, int id);

	/**
	 * permet de rechercher un requin par son identifiant dans l'océan
	 * 
	 * @param un int pour l'indentifiant du requin
	 * @return un requin
	 */
	public Requin getRequinId(int id);

	/**
	 * permet de voir si un requin à cet identifiant dans l'océan
	 * 
	 * @param un int qui est l'identifiant du requin
	 * @return un boolean qui indique si le requin existe ou non
	 */
	public boolean existReqId(int id);

}
