package umc.spring.service.memberService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.config.security.jwt.JwtTokenProvider;
import umc.spring.converter.MemberConverter;
import umc.spring.domain.Member;
import umc.spring.repository.memberRepository.MemberRepository;
import umc.spring.web.dto.MemberResponseDTO;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request) {

        Authentication authentication = jwtTokenProvider.extractAuthentication(request);
        String email = authentication.getName();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        return MemberConverter.toMemberInfoDTO(member);
    }
}
