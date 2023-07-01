package dev.mission.presentation;

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

import dev.mission.application.mission.CreateMissionUseCase;
import dev.mission.application.mission.DeleteMissionUseCase;
import dev.mission.application.mission.GetMissionUseCase;
import dev.mission.application.mission.UpdateMissionUseCase;
import dev.mission.domain.dto.mission.request.CreateMissionRequest;
import dev.mission.domain.dto.mission.request.UpdateMissionRequest;
import dev.mission.exceptions.ServiceException;

@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@DeclareRoles("user")
@RolesAllowed("user")
public class MissionController {
    private final CreateMissionUseCase createMissionUseCase;
    private final GetMissionUseCase getMissionUseCase;
    private final UpdateMissionUseCase updateMissionUseCase;
    private final DeleteMissionUseCase deleteMissionUseCase;

    @Inject
    JsonWebToken jwt;

    @Inject
    public MissionController(CreateMissionUseCase createMissionUseCase, 
    GetMissionUseCase getMissionUseCase, UpdateMissionUseCase updateMissionUseCase,
    DeleteMissionUseCase deleteMissionUseCase) {
        this.createMissionUseCase = createMissionUseCase;
        this.getMissionUseCase = getMissionUseCase;
        this.updateMissionUseCase = updateMissionUseCase;
        this.deleteMissionUseCase = deleteMissionUseCase;
    }
    
    @GET
    @Path("/{hash}")
    @WithSession
    public Uni<Response> getWorkout(@PathParam("hash") final String hash) {
        try {
            return getMissionUseCase.execute(hash)
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
    public Uni<Response> createMission(CreateMissionRequest request) {
        try {
            String userHash = jwt.getClaim("c_hash");
            return createMissionUseCase.execute(request, userHash)
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
    public Uni<Response> updateMission(UpdateMissionRequest request) {

        try {
            String userHash = jwt.getClaim("c_hash");
            return updateMissionUseCase.execute(request, userHash)
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
            return deleteMissionUseCase.execute(hash, userHash)
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
