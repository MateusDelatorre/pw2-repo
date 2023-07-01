package dev.mission.data.repository;

import dev.mission.domain.models.MissionEntity;
import io.smallrye.mutiny.Uni;

public interface MissionRepository {
    Uni<MissionEntity> persist(MissionEntity mission);
    Uni<MissionEntity> findByHash(String hash);
}
