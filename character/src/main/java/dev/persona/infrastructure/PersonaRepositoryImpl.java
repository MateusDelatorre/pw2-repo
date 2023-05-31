package dev.persona.infrastructure;

import dev.persona.data.repository.PersonaRepository;
import dev.persona.domain.models.PersonaEntity;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonaRepositoryImpl implements PersonaRepository, PanacheRepository<PersonaEntity>{

    @Override
    public Uni<PersonaEntity> persist(PersonaEntity persona) {
        return persistAndFlush(persona);
    }

    @Override
    public Uni<PersonaEntity> findByHash(String hash) {
        return find("hash", hash).firstResult();
    }
    
}
