package org.inria.restlet.mta.resources;

import java.util.Collection;

import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.backend.Zone;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class sardineRessource extends ServerResource {

	private Backend backend_;
	private Zone zone_;

	public sardineRessource() {
		super();
		backend_ = (Backend) getApplication().getContext().getAttributes().get("backend");
	}

	@Get("json")
	public Representation getNbSardines() throws Exception {
		int nbSardine = backend_.getDatabase().getNbSardine();
		JSONObject userObject = new JSONObject();
		userObject.put("nombre de sardine", nbSardine); // call a la fonction qui passe a travers toutes les zones de
														// notre ocean et qui compte le nombre de sardine
		return new JsonRepresentation(userObject);
	}

}
