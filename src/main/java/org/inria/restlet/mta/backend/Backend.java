package org.inria.restlet.mta.backend;

import org.inria.restlet.mta.database.api.Ocean;
import org.inria.restlet.mta.database.api.impl.OceanImpl;

public class Backend
{
    /** Database.*/
    private Ocean database_;

    public Backend()
    {
        database_ = new OceanImpl();
    }

    public Ocean getDatabase()
    {
        return database_;
    }

}
