package dev.mission.domain.dto.mission.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CreateMissionRequest {
    private String nome;
    private byte difficulty;
    private String description;
}
