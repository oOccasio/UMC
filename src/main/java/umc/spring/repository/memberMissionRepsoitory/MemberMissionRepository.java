package umc.spring.repository.memberMissionRepsoitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;

import java.util.List;
import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Optional<MemberMission> findMemberMissionByMember(Member member);
    Optional<MemberMission> findMemberMissionByMission(Mission mission);
    Boolean existsMemberMissionByMission(Mission mission);

    Page<MemberMission> findAllMissionsByMember(Member member, PageRequest pageRequest);

}
