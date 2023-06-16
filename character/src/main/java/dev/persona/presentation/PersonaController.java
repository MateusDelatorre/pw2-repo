package dev.persona.presentation;

import dev.persona.application.persona.CreatePersonaUseCase;
import dev.persona.application.persona.DeletePersonaUseCase;
import dev.persona.application.persona.GetPersonaUseCase;
import dev.persona.application.persona.UpdatePersonaUseCase;
import dev.persona.domain.dto.persona.request.CreatePersonaRequest;
import dev.persona.domain.dto.persona.request.UpdatePersonaRequest;
import dev.persona.exceptions.ServiceException;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@DeclareRoles("user")
@RolesAllowed("user")
public class PersonaController {
    private final CreatePersonaUseCase createPersonaUseCase;
    private final GetPersonaUseCase getPersonaUseCase;
    private final UpdatePersonaUseCase updatePersonaUseCase;
    private final DeletePersonaUseCase deletePersonaUseCase;

    @Inject
    JsonWebToken jwt;

    @Inject
    public PersonaController(CreatePersonaUseCase createPersonaUseCase, 
    GetPersonaUseCase getPersonaUseCase, UpdatePersonaUseCase updatePersonaUseCase,
    DeletePersonaUseCase deletePersonaUseCase) {
        this.createPersonaUseCase = createPersonaUseCase;
        this.getPersonaUseCase = getPersonaUseCase;
        this.updatePersonaUseCase = updatePersonaUseCase;
        this.deletePersonaUseCase = deletePersonaUseCase;
    }
    
    @GET
    @Path("/{hash}")
    @WithSession
    public Uni<Response> getWorkout(@PathParam("hash") final String hash) {
        try {
            return getPersonaUseCase.execute(hash)
                    .map(response -> Response.status(Status.OK).entity(response).build())
                    .log()
                    .onFailure().transform(e -> {
                        String message = e.getMessage();
                        throw new ServiceException(
                                message,
                                Response.Status.BAD_REQUEST);
                    });
        } catch (Exception e) {
            String message = e.getMessage();
            throw new ServiceException(
                    message,
                    Response.Status.BAD_REQUEST);
        }
    }

    @POST
    @Path("/create")
    @WithSession
    public Uni<Response> createPersona(CreatePersonaRequest request) {
        try {
            String userHash = jwt.getClaim("c_hash");
            return createPersonaUseCase.execute(request, userHash)
                    .map(response -> Response.status(Status.OK).build())
                    .log()
                    .onFailure().transform(e -> {
                        String message = e.getMessage();
                        throw new ServiceException(
                                message,
                                Response.Status.BAD_REQUEST);
                    });
        } catch (Exception e) {
            String message = e.getMessage();
            throw new ServiceException(
                    message,
                    Response.Status.BAD_REQUEST);
        }
    }

    @POST
    @Path("/update")
    @WithSession
    public Uni<Response> updatePersona(UpdatePersonaRequest request) {

        try {
            String userHash = jwt.getClaim("c_hash");
            return updatePersonaUseCase.execute(request, userHash)
                    .map(response -> Response.status(Status.OK).entity(response).build())
                    .log()
                    .onFailure().transform(e -> {
                        String message = e.getMessage();
                        throw new ServiceException(
                                message,
                                Response.Status.BAD_REQUEST);
                    });
        } catch (Exception e) {
            String message = e.getMessage();
            throw new ServiceException(
                    message,
                    Response.Status.BAD_REQUEST);
        }
    }

    @DELETE
    @Path("/delete/{hash}")
    @WithSession
    public Uni<Response> deleteWorkout(@PathParam("hash") final String hash) {
        try {
            String userHash = jwt.getClaim("c_hash");
            return deletePersonaUseCase.execute(hash, userHash)
                    .map(response -> Response.status(Status.OK).entity(response).build())
                    .log()
                    .onFailure().transform(e -> {
                        String message = e.getMessage();
                        throw new ServiceException(
                                message,
                                Response.Status.BAD_REQUEST);
                    });
        } catch (Exception e) {
            String message = e.getMessage();
            throw new ServiceException(
                    message,
                    Response.Status.BAD_REQUEST);
        }
    }

}
