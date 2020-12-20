package org.inria.restlet.mta.resources;

import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.backend.Requin;
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

	@Get("json")
	public Representation getRequin() throws Exception {
		String id = (String) getRequest().getAttributes().get("reqId");
		int id_Req = Integer.valueOf(id);
		Requin req = backend_.getDatabase().getRequinyId(id_Req);
		JSONObject resultObject = new JSONObject();
		resultObject.put("vie restante", req.getLifeRemaining());
		resultObject.put("la zone actuelle", req.getZone());
		JsonRepresentation result = new JsonRepresentation(resultObject);
		return result;
	}
}
