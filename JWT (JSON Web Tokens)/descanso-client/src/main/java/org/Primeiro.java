package org;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/primeiro")
public class Primeiro {

    @RestClient
    @Inject
    Convert remoto;
    
    @GET
    @Path("/soma/{a}/{b}")
    @RolesAllowed("User")
    public int soma(@PathParam("a") int a, @PathParam("b") int b){
        return a + b;
    }

    @POST
    @Path("/kmtom/{a}")
    @RolesAllowed("User")
    public double kmToMiles(@PathParam("a") double a){
        return remoto.kmToMiles(a);
    }

    @GET
    @Path("/nostokm/{a}")
    @RolesAllowed("User")
    @Produces(MediaType.APPLICATION_JSON)
    public Data nosToKm(@PathParam("a") double a){
        return remoto.nosToKm(a);
    }

}
