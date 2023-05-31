package dev.persona.application.persona;

import dev.persona.data.repository.PersonaRepository;
import dev.persona.domain.dto.persona.request.CreatePersonaRequest;
import dev.persona.domain.dto.persona.response.CreatePersonaResponse;
import dev.persona.domain.models.PersonaEntity;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreatePersonaUseCase {
    private final PersonaRepository personaRepository;

    public CreatePersonaUseCase(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public Uni<CreatePersonaResponse> execute(final CreatePersonaRequest request, final String userHash) {
        PersonaEntity persona = new PersonaEntity();
        persona.setNome(request.getNome());
        persona.setUserHash(userHash);

        return personaRepository.persist(persona)
        .map(v -> {
            CreatePersonaResponse response = new CreatePersonaResponse();
            response.setHash(persona.getHash());
            response.setNome(persona.getNome());
            return response;
        });
    }
}
