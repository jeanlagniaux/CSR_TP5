package org.inria.restlet.mta.resources;

import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.backend.Zone;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class zonesRessource extends ServerResource {

	private Backend backend_;
	private Zone zone_;

	public zonesRessource() {
		super();
		backend_ = (Backend) getApplication().getContext().getAttributes().get("backend");
	}

	@Get("json")
	public Representation getZone() throws Exception {
		String zoneId = (String) getRequest().getAttributes().get("zone_id");
		// on recup la zone en string pour ensuite la séparer et la passer en deu int X
		// et Y
		char x = zoneId.charAt(0);
		char y = zoneId.charAt(1);
		int cord_X = Character.getNumericValue(x);
		int cord_Y = Character.getNumericValue(y);
		zone_ = backend_.getDatabase().getzoneByCoor(cord_X, cord_Y); // on fait la recherche avec nos deux int X et Y
		JSONObject userObject = new JSONObject();
		userObject.put("nombre de sardine", zone_.getNbSardine());
		userObject.put("y a t'il un requin ?", zone_.getHasRequin());
		if (zone_.getHasRequin()) {
			userObject.put("id du requin", zone_.getRequin().getIdRequin()); // alors on affiche l'identifiant du requin
		}
		return new JsonRepresentation(userObject);
	}
}
