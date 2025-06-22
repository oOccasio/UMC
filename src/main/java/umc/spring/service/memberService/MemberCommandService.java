package umc.spring.service.memberService;

import umc.spring.domain.Member;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

public interface MemberCommandService {

    public Member joinMember(MemberRequestDTO.JoinDto request);

    MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request);
}
