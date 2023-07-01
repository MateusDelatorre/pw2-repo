package dev.mission.application.mission;

import dev.mission.data.repository.MissionRepository;
import dev.mission.domain.dto.mission.request.CreateMissionRequest;
import dev.mission.domain.dto.mission.response.CreateMissionResponse;
import dev.mission.domain.models.MissionEntity;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateMissionUseCase {
    private final MissionRepository missionRepository;

    public CreateMissionUseCase(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    public Uni<CreateMissionResponse> execute(final CreateMissionRequest request, final String userHash) {
        MissionEntity mission = new MissionEntity();
        mission.setNome(request.getNome());
        mission.setUserHash(userHash);

        return missionRepository.persist(mission)
        .map(v -> {
            CreateMissionResponse response = new CreateMissionResponse();
            response.setHash(mission.getHash());
            response.setNome(mission.getNome());
            return response;
        });
    }
}
