package dev.persona.domain.models;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**Persona Entity. */
@Entity
@Getter@Setter
public class PersonaEntity {
    /** Primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    /** Entity hash id. */
    private String hash;

    /** Persona name. */
    @NotNull
    private String nome;

    /** Persona current level */
    @NotNull
    private int level;

    /** Persona current xp */
    @NotNull
    private int xp;

    /** Hash from the user database owner. */
    private String userHash;

    /**PersonaEntity Constructor */
    public PersonaEntity() {
        super();
        this.hash = UUID.randomUUID().toString();
        this.level = 0;
        this.xp = 0;
    }
}
