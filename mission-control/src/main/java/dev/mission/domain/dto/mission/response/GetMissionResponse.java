package dev.mission.domain.dto.mission.response;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class GetMissionResponse {
    private String hash;
    private String nome;
    private byte difficulty;
    private String description;
    private Boolean completed;
}
