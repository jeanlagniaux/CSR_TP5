package org.inria.restlet.mta.resources;

import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.internals.User;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class TweetRessource extends ServerResource {

	/** Backend. */
	private Backend backend_;

	/** Utilisateur géré par cette resource. */
	private User user_;

	/**
	 * Constructor. Call for every single user request.
	 */
	public TweetRessource() {
		backend_ = (Backend) getApplication().getContext().getAttributes().get("backend");
	}

	@Post("json")
	public Representation postTweet(JsonRepresentation representation) throws Exception {
		JSONObject object = representation.getJsonObject();
		String userIdString = (String) getRequest().getAttributes().get("userId");
		int userId = Integer.valueOf(userIdString);
		if (backend_.getDatabase().exists(userId)) {
			String tweet = object.getString("tweet");
			backend_.getDatabase().addTweet(userId, tweet);
			JSONObject response = new JSONObject();
			response.put("result", "DONE");
			//response.put("tweet", user_.getTweets());
			return new JsonRepresentation(response);
		} else {
			JSONObject response = new JSONObject();
			response.put("result", "DOES NOT EXIST");
			return new JsonRepresentation(response);
		}
	}
	
	@Get("json")
	public Representation getTweet(JsonRepresentation representation) throws Exception {
		String userIdString = (String) getRequest().getAttributes().get("userId");
		int userId = Integer.valueOf(userIdString);
		user_ = backend_.getDatabase().getUser(userId);

		JSONObject userObject = new JSONObject();
		userObject.put("name", user_.getName());
		userObject.put("age", user_.getAge());
		userObject.put("id", user_.getId());
		userObject.put("tweets", user_.getTweets());

		return new JsonRepresentation(userObject);
	}

}
