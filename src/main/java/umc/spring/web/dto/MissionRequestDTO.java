package umc.spring.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MissionRequestDTO {

    @Getter
    @Builder
    public static class addMissionDTO{

        @NotNull
        Integer reward;

        @NotNull
        LocalDateTime deadline;

        @NotNull
        String missionSpec;

    }
}
