package org.inria.restlet.mta.database.api;

import org.inria.restlet.mta.backend.ZoneV2;

public interface OceanV2 {

	public boolean hasRequin();

	public ZoneV2 getzoneByCoor(int x, int y);

	public void deplacement(ZoneV2 zone);
	
	int getNbRequin();
	
	String getListRequin();
	
	public ZoneV2[][] getCarte();


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
