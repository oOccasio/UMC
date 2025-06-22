package umc.spring.service.memberMissionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.mapping.MemberMission;

public interface MemberMissionQueryService {

    Page<MemberMission> getMemberChallengingMission(Long memberId, Integer page);

}
