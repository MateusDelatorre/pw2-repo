package dev.mission.application.mission;

import dev.mission.data.repository.MissionRepository;
import dev.mission.domain.dto.mission.request.UpdateMissionRequest;
import dev.mission.domain.dto.mission.response.UpdateMissionResponse;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UpdateMissionUseCase {
    private final MissionRepository missionRepository;

    public UpdateMissionUseCase(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    public Uni<UpdateMissionResponse> execute(final UpdateMissionRequest request, final String userHash) {
        return missionRepository.findByHash(request.getHash()).onItem().ifNotNull()
        .transformToUni(mission -> {
            if (!userHash.equals(mission.getUserHash())) {
                throw new IllegalArgumentException("Proibido");
            }
            mission.setNome(request.getNome());
            return missionRepository.persist(mission)
            .map(v ->{
                UpdateMissionResponse response = new UpdateMissionResponse();
                response.setHash(v.getHash());
                response.setNome(v.getNome());
                response.setDifficulty(v.getDifficulty());
                response.setDescription(v.getDescription());
                response.setCompleted(v.getCompleted());
                return response;
            });
        });
    }
}
