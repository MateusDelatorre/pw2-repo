package dev.persona.domain.dto.persona.response;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class GetPersonaResponse {
    private String hash;
    private String nome;
    private int level;
    private int xp;
}
