package dev.persona.domain.dto.persona.request;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UpdatePersonaRequest {
    private String hash;
    private String nome;
    private int level;
    private int xp;
}
