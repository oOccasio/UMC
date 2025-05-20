package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public class MissionConverter {

    public static Mission toMission(MissionRequestDTO.addMissionDTO request){
        return Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .build();

    }

    public static MissionResponseDTO.addMissionDTO toMissionResponseDTO(Mission mission){
        return MissionResponseDTO.addMissionDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MissionResponseDTO.getStoreMissionDTO toStoreMissionResponseDTO(Mission mission){
        return MissionResponseDTO.getStoreMissionDTO.builder()
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .missionSpec(mission.getMissionSpec())
                .build();
    }

    public static MissionResponseDTO.getStoreMissionDTOList toStoreMissionResponseDTOList(Page<Mission> missionList){

        List<MissionResponseDTO.getStoreMissionDTO> storeMissionDTOList = missionList.stream()
                .map(MissionConverter::toStoreMissionResponseDTO).toList();

        return MissionResponseDTO.getStoreMissionDTOList.builder()
                .missionList(storeMissionDTOList)
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .totalPage(missionList.getTotalPages())
                .listSize(storeMissionDTOList.size())
                .build();

    }
}
