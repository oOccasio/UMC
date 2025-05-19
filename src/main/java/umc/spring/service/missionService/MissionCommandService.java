package umc.spring.service.missionService;

import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequestDTO;

public interface MissionCommandService {

    public Mission addMission(MissionRequestDTO.addMissionDTO request);
}
