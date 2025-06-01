package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.validation.annotation.ExistMission;

public class MemberMissionRequestDTO {

    @Getter
    @Builder
    public static class addMemberMission{


        @ExistMission
        Long missionId;

        @NotNull
        MissionStatus status;


    }
}
