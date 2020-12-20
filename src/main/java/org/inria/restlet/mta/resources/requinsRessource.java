package org.inria.restlet.mta.resources;

import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.backend.Requin;
import org.inria.restlet.mta.backend.Zone;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class requinsRessource extends ServerResource {

	private Backend backend_;

	public requinsRessource() {
		super();
		backend_ = (Backend) getApplication().getContext().getAttributes().get("backend");
	}

	/**
	 * permet de retourner le nombre de requin
	 */
	@Get("json")
	public Representation getNbRequin() throws Exception {
		int nbRequin = backend_.getDatabase().getNbRequin();
		JSONObject userObject = new JSONObject();
		userObject.put("nombre de requin dans le systeme", nbRequin);
		return new JsonRepresentation(userObject);
	}

	/**
	 * Creation du requin avec une zone et un identifiant depuis methode post
	 */
	@Post("json")
	public Representation createRequin(JsonRepresentation representation) throws Exception {
		int xResp = 0;
		int yResp = 0;
		JSONObject object = representation.getJsonObject();
		String coord = object.getString("coord");
		char x = coord.charAt(0);
		char y = coord.charAt(1);
		int cord_X = Character.getNumericValue(x);
		int cord_Y = Character.getNumericValue(y);
		String idString = object.getString("id");
		int id = Integer.parseInt(idString);

		Requin req = backend_.getDatabase().createRequinId(backend_.getDatabase().getzoneByCoor(cord_X, cord_Y), id);

		if (!req.equals(null)) {
			JSONObject resultObject = new JSONObject();
			resultObject.put("vie restante", req.getLifeRemaining());
			xResp = req.getZone().getCoordX();
			yResp = req.getZone().getCoordY();
			resultObject.put("la zone actuelle", "" + x + "," + y);
			JsonRepresentation result = new JsonRepresentation(resultObject);
			return result;
		} else {
			JSONObject resultObject = new JSONObject();
			resultObject.put("info", "il existait deja un requin dans cette zone");
			JsonRepresentation result = new JsonRepresentation(resultObject);
			return result;
		}
	}

}
