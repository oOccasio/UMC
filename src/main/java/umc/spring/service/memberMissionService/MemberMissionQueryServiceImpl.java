package umc.spring.service.memberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.memberMissionRepsoitory.MemberMissionRepository;
import umc.spring.repository.memberRepository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {


    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Page<MemberMission> getMemberChallengingMission(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        return memberMissionRepository.findAllMissionsByMember(member, PageRequest.of(page, 10));

    }


}
