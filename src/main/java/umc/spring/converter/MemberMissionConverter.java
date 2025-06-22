package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMissionResponseDTO;

import java.util.List;

public class MemberMissionConverter {

    public static MemberMission toMemberMission(
            MemberMissionRequestDTO.addMemberMission request,
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

    public static MemberMissionResponseDTO.getMemberChallengingMission
    toMemberChallengingMissionResponseDTO(MemberMission memberMission){

        return MemberMissionResponseDTO.getMemberChallengingMission.builder()
                .missionStatus(memberMission.getMissionStatus())
                .mission(memberMission.getMission())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .reward(memberMission.getMission().getReward())
                .deadline(memberMission.getMission().getDeadline())
                .build();
    }

    public static MemberMissionResponseDTO.getMemberChallengingMissionList getMemberChallengingMissionList
            (Page<MemberMission> memberMissionList){

        List<MemberMissionResponseDTO.getMemberChallengingMission> memberChallengingMissionList
                = memberMissionList.stream()
                .map(MemberMissionConverter::toMemberChallengingMissionResponseDTO).toList();

        return MemberMissionResponseDTO.getMemberChallengingMissionList.builder()
                .isLast(memberMissionList.isLast())
                .isFirst(memberMissionList.isFirst())
                .totalPage(memberMissionList.getTotalPages())
                .listSize(memberChallengingMissionList.size())
                .memberChallengingMissionList(memberChallengingMissionList)
                .build();

    }
}
