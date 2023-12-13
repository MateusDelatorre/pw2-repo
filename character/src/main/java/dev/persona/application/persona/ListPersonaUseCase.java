package dev.persona.application.persona;


import java.util.ArrayList;
import java.util.List;

import dev.persona.data.repository.PersonaRepository;
import dev.persona.domain.dto.persona.response.ListUserPersonaResponse;
import dev.persona.domain.models.PersonaEntity;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ListPersonaUseCase {
    private final PersonaRepository workoutRepository;

    public ListPersonaUseCase(PersonaRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public Uni<List<ListUserPersonaResponse>> execute(final String userHash) {
        return workoutRepository.listUserPersona(userHash)
        .map(v -> {
            List<ListUserPersonaResponse> response = new ArrayList<>();
            for (PersonaEntity persona:v) {
                ListUserPersonaResponse obj = new ListUserPersonaResponse();
                obj.setHash(persona.getHash());
                obj.setNome(persona.getNome());
                response.add(obj);
            }
            return response;
        });
    }
}