package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMissionResponseDTO;

public class MemberMissionConverter {

    public static MemberMission toMemberMission(MemberMissionRequestDTO.addMemberMission request,
                                                Member member,
                                                Mission mission){
        return MemberMission.builder()
                .missionStatus(request.getStatus())
                .member(member)
                .mission(mission)
                .build();

    }

    public static MemberMissionResponseDTO.addMemberMission toMemberMissionResponseDTO(
            MemberMission memberMission){

        return MemberMissionResponseDTO.addMemberMission.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }
}
