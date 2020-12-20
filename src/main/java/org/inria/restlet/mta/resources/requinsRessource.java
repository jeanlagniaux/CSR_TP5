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

	@Get("json")
	public Representation getNbRequin() throws Exception {
		int nbRequin = backend_.getDatabase().getNbRequin();
		JSONObject userObject = new JSONObject();
		userObject.put("nombre de requin dans le systeme", nbRequin);
		return new JsonRepresentation(userObject);
	}

	@Post("json")
	public Representation createRequin(JsonRepresentation representation) throws Exception {
		JSONObject object = representation.getJsonObject();
		String coord = object.getString("coord");
		int cord_X = Integer.valueOf(coord.charAt(0));
		int cord_Y = Integer.valueOf(coord.charAt(1));

		// Save the user
		Requin req = backend_.getDatabase().createRequin(backend_.getDatabase().getzoneByCoor(cord_X, cord_Y));

		if (!req.equals(null)) {
			JSONObject resultObject = new JSONObject();
			resultObject.put("vie restante", req.getLifeRemaining());
			resultObject.put("la zone actuelle", req.getZone());
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
