package org.inria.restlet.mta.database.api;

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

	public boolean hasRequin();

	public Zone getzoneByCoor(int x, int y);

	public void deplacement(Requin req);
	
	int getNbRequin();
	
	public Zone[][] getCarte();


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
