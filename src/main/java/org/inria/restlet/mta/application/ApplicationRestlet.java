package org.inria.restlet.mta.application;


import org.inria.restlet.mta.resources.requinRessource;
import org.inria.restlet.mta.resources.requinsRessource;
import org.inria.restlet.mta.resources.sardineRessource;
import org.inria.restlet.mta.resources.zonesRessource;
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
public class ApplicationRestlet extends Application
{
//test
    public ApplicationRestlet(Context context)
    {
        super(context);
    }

    @Override
    public Restlet createInboundRoot()
    {
        Router router = new Router(getContext());
        router.attach("/zones/{zone_id}", zonesRessource.class);
        router.attach("/requin", requinsRessource.class);
        router.attach("/requin/{reqId}", requinRessource.class);
        router.attach("/sardine", sardineRessource.class);
        return router;
    }
}
