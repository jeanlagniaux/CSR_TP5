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

public class requinRessource extends ServerResource {

	private Backend backend_;
	private Requin req;

	public requinRessource() {
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

	// ajouter au requin un identifiant et créer une méthode dans l'océean qui recherche le requin de cette identifiant
	@Get("json")
	public Representation getRequin() throws Exception {
		String req = (String) getRequest().getAttributes().get("requin_id");
		int id_Req = Integer.valueOf(req);
		
		// TODO
		
		//req = backend_.getDatabase().getRequinById();

		JSONObject resultObject = new JSONObject();
		//resultObject.put("vie restante", req.getLifeRemaining());
		//resultObject.put("la zone actuelle", req.getZone());
		JsonRepresentation result = new JsonRepresentation(resultObject);
		return result;
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

		// generate result

	}

}
