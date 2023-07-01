package dev.mission.domain.models;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**Mission Entity. */
@Entity
@Getter@Setter
public class MissionEntity extends PanacheEntityBase{
    /** Primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    /** Entity hash id. */
    private String hash;

    /** Mission name. */
    @NotNull
    private String nome;

    /** Mission difficulty level */
    @NotNull
    private byte difficulty;

    /** Mission description xp */
    @NotNull
    private String description;

    /** Mission current current status */
    @NotNull
    private Boolean completed;

    /** Hash from the user database owner. */
    private String userHash;

    /**MissionEntity Constructor */
    public MissionEntity() {
        super();
        this.hash = UUID.randomUUID().toString();
        this.description = "";
        this.difficulty = 2;
        this.completed = false;
    }
}
