package dev.persona.data.repository;

import java.util.List;

import dev.persona.domain.models.PersonaEntity;
import io.smallrye.mutiny.Uni;

public interface PersonaRepository {
    Uni<PersonaEntity> persist(PersonaEntity persona);
    Uni<PersonaEntity> findByHash(String hash);
    Uni<List<PersonaEntity>> listUserPersona(String hash);
    Uni<Long> deletePersona(PersonaEntity workout);
}
