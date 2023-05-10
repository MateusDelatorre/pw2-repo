package org;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.annotation.security.RolesAllowed;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.oidc.token.propagation.AccessToken;

@RegisterRestClient(baseUri = "https://localhost:8445/api/convert")
@AccessToken
public interface Convert {

    @POST
    @Path("/kmtom/{a}")
    @RolesAllowed("User")
    double kmToMiles(@PathParam("a") double a);

    @GET
    @Path("/nostokm/{a}")
    @RolesAllowed("User")
    @Produces(MediaType.APPLICATION_JSON)
    Data nosToKm(@PathParam("a") double a);
    
}
