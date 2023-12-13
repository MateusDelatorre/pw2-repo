package dev.mission.infrastructure;

import java.util.List;

import dev.mission.data.repository.MissionRepository;
import dev.mission.domain.models.MissionEntity;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MissionRepositoryImpl implements MissionRepository, PanacheRepository<MissionEntity>{

    @Override
    public Uni<MissionEntity> persist(MissionEntity mission) {
        return persistAndFlush(mission);
    }

    @Override
    public Uni<MissionEntity> findByHash(String hash) {
        return find("hash", hash).firstResult();
    }
    
    @Override
    public Uni<List<MissionEntity>> listUserMissions(String hash) {
        return find("userHash", hash).list();
    }

    @Override
    public Uni<Long> deleteMission(MissionEntity mission) {
        return delete("hash", mission.getHash());
    }
}
