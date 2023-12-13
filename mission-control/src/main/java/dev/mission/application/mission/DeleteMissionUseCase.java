package dev.mission.application.mission;

import dev.mission.data.repository.MissionRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeleteMissionUseCase {
    private final MissionRepository missionRepository;

    public DeleteMissionUseCase(MissionRepository missionRepository){
        this.missionRepository = missionRepository;
    }

    public Uni<Object> execute(final String hash, final String userHash) {
        return missionRepository.findByHash(hash)
        .onItem().ifNotNull()
        .transformToUni(mission -> {
            if (!userHash.equals(mission.getUserHash())) {
                throw new IllegalArgumentException("Proibido");
            }
            return missionRepository.deleteMission(mission);
        });
    }
}
