package dev.mission.domain.dto.mission.request;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UpdateMissionRequest {
    private String hash;
    private String nome;
    private byte difficulty;
    private String description;
    private Boolean completed;
}
