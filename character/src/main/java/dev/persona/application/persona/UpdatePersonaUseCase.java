package dev.persona.application.persona;

import dev.persona.data.repository.PersonaRepository;
import dev.persona.domain.dto.persona.request.UpdatePersonaRequest;
import dev.persona.domain.dto.persona.response.UpdatePersonaResponse;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UpdatePersonaUseCase {
    private final PersonaRepository personaRepository;

    public UpdatePersonaUseCase(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public Uni<UpdatePersonaResponse> execute(final UpdatePersonaRequest request, final String userHash) {
        return personaRepository.findByHash(request.getHash()).onItem().ifNotNull()
        .transformToUni(persona -> {
            if (!userHash.equals(persona.getUserHash())) {
                throw new IllegalArgumentException("Proibido");
            }
            persona.setNome(request.getNome());
            persona.setLevel(request.getLevel());
            persona.setXp(request.getXp());
            return personaRepository.persist(persona)
            .map(v ->{
                UpdatePersonaResponse response = new UpdatePersonaResponse();
                response.setHash(v.getHash());
                response.setNome(v.getNome());
                response.setLevel(v.getLevel());
                response.setXp(v.getXp());
                return response;
            });
        });
    }
}
