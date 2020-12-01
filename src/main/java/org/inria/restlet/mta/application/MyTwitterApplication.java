package org.inria.restlet.mta.application;


import org.inria.restlet.mta.resources.TweetRessource;
import org.inria.restlet.mta.resources.UserResource;
import org.inria.restlet.mta.resources.UsersResource;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 *
 * Application.
 *
 * @author msimonin
 *
 */
public class MyTwitterApplication extends Application
{
//test
    public MyTwitterApplication(Context context)
    {
        super(context);
    }

    @Override
    public Restlet createInboundRoot()
    {
        Router router = new Router(getContext());
        router.attach("/users", UsersResource.class);
        router.attach("/users/{userId}", UserResource.class);
        router.attach("/users/{userId}/tweets", TweetRessource.class);
        router.attach("/users/tweets", UsersResource.class);
        return router;
    }
}
