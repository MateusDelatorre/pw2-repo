package dev.mission.application.mission;

import dev.mission.data.repository.MissionRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeleteMissionUseCase {
    private final MissionRepository personaRepository;

    public DeleteMissionUseCase(MissionRepository personaRepository){
        this.personaRepository = personaRepository;
    }

    public Uni<Void> execute(final String hash, final String userHash) {
        return personaRepository.findByHash(hash)
        .onItem().ifNotNull()
        .transformToUni(persona -> {
            if (!userHash.equals(persona.getUserHash())) {
                throw new IllegalArgumentException("Proibido");
            }
            return persona.delete();
        });
    }
}
