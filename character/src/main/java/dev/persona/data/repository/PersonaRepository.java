package dev.persona.data.repository;

import dev.persona.domain.models.PersonaEntity;
import io.smallrye.mutiny.Uni;

public interface PersonaRepository {
    Uni<PersonaEntity> persist(PersonaEntity persona);
    Uni<PersonaEntity> findByHash(String hash);
}
