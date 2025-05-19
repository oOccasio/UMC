package umc.spring.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MissionRequestDTO {

    @Getter
    @Builder
    public static class addMissionDTO{

        Integer reward;

        LocalDateTime deadline;

        String missionSpec;

    }
}
