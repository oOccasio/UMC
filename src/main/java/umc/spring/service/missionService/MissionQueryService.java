package umc.spring.service.missionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;

public interface MissionQueryService {

    Page<Mission> getStoreMission(Long storeId, Integer page);
}
