package dev.persona.application.persona;

import dev.persona.data.repository.PersonaRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeletePersonaUseCase {
    private final PersonaRepository personaRepository;

    public DeletePersonaUseCase(PersonaRepository personaRepository){
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
