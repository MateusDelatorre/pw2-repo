package dev.mission.application.mission;

import dev.mission.data.repository.MissionRepository;
import dev.mission.domain.dto.mission.response.GetMissionResponse;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GetMissionUseCase {
    private final MissionRepository personaRepository;

    public GetMissionUseCase(MissionRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public Uni<GetMissionResponse> execute(final String hash) {
        return personaRepository.findByHash(hash)
        .map(v -> {
            GetMissionResponse response = new GetMissionResponse();
            response.setHash(v.getHash());
            response.setNome(v.getNome());
            response.setDifficulty(v.getDifficulty());
            response.setDescription(v.getDescription());
            response.setCompleted(v.getCompleted());
            return response;
        });
    }
}
