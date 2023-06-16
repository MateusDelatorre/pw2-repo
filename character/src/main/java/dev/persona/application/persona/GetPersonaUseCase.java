package dev.persona.application.persona;

import dev.persona.data.repository.PersonaRepository;
import dev.persona.domain.dto.persona.response.GetPersonaResponse;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GetPersonaUseCase {
    private final PersonaRepository personaRepository;

    public GetPersonaUseCase(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public Uni<GetPersonaResponse> execute(final String hash) {
        return personaRepository.findByHash(hash)
        .map(v -> {
            GetPersonaResponse response = new GetPersonaResponse();
            response.setHash(v.getHash());
            response.setNome(v.getNome());
            response.setLevel(v.getLevel());
            response.setXp(v.getXp());
            return response;
        });
    }
}
