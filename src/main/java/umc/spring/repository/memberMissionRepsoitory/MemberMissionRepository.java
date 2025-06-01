package umc.spring.repository.memberMissionRepsoitory;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Optional<MemberMission> findMemberMissionByMember(Member member);
    Optional<MemberMission> findMemberMissionByMission(Mission mission);
    boolean existsMemberMissionByMission(Mission mission);
}
