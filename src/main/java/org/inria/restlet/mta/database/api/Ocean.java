package org.inria.restlet.mta.database.api;

import org.inria.restlet.mta.internals.PoissonPilote;
import org.inria.restlet.mta.internals.Requin;
import org.inria.restlet.mta.internals.Zone;

/**
 *
 * Interface to the database.
 *
 * @author msimonin
 *
 */
public interface Ocean {

	public boolean hasRequin();

	public boolean hasPoisonPilote();

	public Zone getzoneByCoor(int x, int y);

	public Zone choixZoneReq(Requin req);

	public void deplacementReq(Requin req, Zone zoneArr);

	int getNbRequin();

	public Zone[][] getCarte();
	
	public void ppSaccrocher(Zone zone, PoissonPilote pp);
	
	public void ppSeDecrocher(PoissonPilote pp);

	/**
	 *
	 * Create a new user in the database.
	 *
	 * @param name The name of the user
	 * @param age  The age of the user
	 * @return the new user.
	 */
	// User createUser(String name, int age);

	/**
	 *
	 * Returns the list of users.
	 *
	 * @return the list of users
	 */
	// Collection<User> getUsers();

	/**
	 * Returns the user with a given id.
	 *
	 * @return the user
	 */
	// User getUser(int id);

}
