package dev.mission.application.mission;


import java.util.ArrayList;
import java.util.List;

import dev.mission.data.repository.MissionRepository;
import dev.mission.domain.dto.mission.response.ListUserMissionsResponse;
import dev.mission.domain.models.MissionEntity;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ListMissionsUseCase {
    private final MissionRepository missionRepository;

    public ListMissionsUseCase(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    public Uni<List<ListUserMissionsResponse>> execute(final String userHash) {
        return missionRepository.listUserMissions(userHash)
        .map(v -> {
            List<ListUserMissionsResponse> response = new ArrayList<>();
            for (MissionEntity persona:v) {
                ListUserMissionsResponse obj = new ListUserMissionsResponse();
                obj.setHash(persona.getHash());
                obj.setNome(persona.getNome());
                response.add(obj);
            }
            return response;
        });
    }
}