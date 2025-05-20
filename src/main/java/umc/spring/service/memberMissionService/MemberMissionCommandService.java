package umc.spring.service.memberMissionService;

import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionRequestDTO;

public interface MemberMissionCommandService {

    public MemberMission addMemberMission(MemberMissionRequestDTO.addMemberMission request,
                                          Long memberId);

}
