package umc.spring.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDateTime;
import java.util.List;

public class MemberMissionResponseDTO {

    @Getter
    @Builder
    public static class addMemberMission{

        Long memberMissionId;

        LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class getMemberChallengingMissionList{
        List<getMemberChallengingMission> memberChallengingMissionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class getMemberChallengingMission{

        MissionStatus missionStatus;
        Integer reward;
        LocalDateTime deadline;
        String missionSpec;
        Mission mission;

    }

}
