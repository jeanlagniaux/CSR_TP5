package org.inria.restlet.mta.resources;

import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.backend.Requin;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class requinRessource extends ServerResource {

	private Backend backend_;

	public requinRessource() {
		super();
		backend_ = (Backend) getApplication().getContext().getAttributes().get("backend");
	}

	/**
	 * permet de retourner des informations sur un requin en particulier en fonction
	 * de son identifiant donnée par l'utilisateur
	 */
	@Get("json")
	public Representation getRequin() throws Exception {
		String id = (String) getRequest().getAttributes().get("req_Id");
		int id_Req = Integer.valueOf(id);
		JSONObject resultObject = new JSONObject();
		if (backend_.getDatabase().existReqId(id_Req)) {
			Requin req = backend_.getDatabase().getRequinId(id_Req);
			resultObject.put("vie restante", req.getLifeRemaining());
			resultObject.put("la zone actuelle est", req.getZone().getCoordX() + "," + req.getZone().getCoordY());
		} else {
			resultObject.put("erreur", "le requin " + id_Req + " n'existe pas dans cette océan..");
		}
		JsonRepresentation result = new JsonRepresentation(resultObject);
		return result;
	}
}
