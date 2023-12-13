package dev.mission.data.repository;

import java.util.List;

import dev.mission.domain.models.MissionEntity;
import io.smallrye.mutiny.Uni;

public interface MissionRepository {
    Uni<MissionEntity> persist(MissionEntity mission);
    Uni<MissionEntity> findByHash(String hash);
    Uni<List<MissionEntity>> listUserMissions(String hash);
    Uni<Long> deleteMission(MissionEntity workout);
}
