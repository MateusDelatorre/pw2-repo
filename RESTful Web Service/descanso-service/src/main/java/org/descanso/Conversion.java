package org.descanso;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.annotation.security.RolesAllowed;

@Path("api/convert")
public class Conversion {
    
    @POST
    @Path("/kmtom/{a}")
    @RolesAllowed("User")
    public double kmToMiles(@PathParam("a") double a){
        return a * 0.62;
    }

    @GET
    @Path("/nostokm/{a}")
    @RolesAllowed("User")
    @Produces(MediaType.APPLICATION_JSON)
    public Data nosToKm(@PathParam("a") double a){
        Data d = new Data();
        d.data = a * 1.852;
        return d;
    }
}
