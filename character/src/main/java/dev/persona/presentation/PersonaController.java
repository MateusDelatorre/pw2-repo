package dev.persona.presentation;

import dev.persona.application.persona.CreatePersonaUseCase;
import dev.persona.domain.dto.persona.request.CreatePersonaRequest;
import dev.persona.exceptions.ServiceException;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
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

    @Inject
    JsonWebToken jwt;

    @Inject
    public PersonaController(CreatePersonaUseCase createPersonaUseCase) {
        this.createPersonaUseCase = createPersonaUseCase;
    }

    @POST
    @Path("/create")
    @WithSession
    public Uni<Response> createWorkout(CreatePersonaRequest request) {
        try {
            String userHash = jwt.getClaim("c_hash");
            return createPersonaUseCase.execute(request, userHash)
                    .map(response -> Response.status(Status.CREATED).entity(response).build())
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
