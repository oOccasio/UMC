package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.memberMissionRepsoitory.MemberMissionRepository;
import umc.spring.repository.missionRepository.MissionRepository;
import umc.spring.validation.annotation.DuplicatedMission;

@Component
@RequiredArgsConstructor
public class DuplicatedMissionValidator implements ConstraintValidator<DuplicatedMission, Long> {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    @Override
    public void initialize(DuplicatedMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        if (missionId == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_NOT_FOUND.toString())
                    .addConstraintViolation();
            return false;
        }

        // 미션 존재 여부 체크
        Mission mission = missionRepository.findById(missionId).orElse(null);
        if (mission == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_NOT_FOUND.toString())
                    .addConstraintViolation();
            return false;
        }

        // 중복 도전 여부 체크
        var optionalMemberMission = memberMissionRepository.findMemberMissionByMission(mission);
        if (optionalMemberMission.isPresent()) {
            MemberMission memberMission = optionalMemberMission.get();
            if (memberMission.getMissionStatus() == MissionStatus.CHALLENGING) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(ErrorStatus.ALREADY_CHALLENGING_MISSION.toString())
                        .addConstraintViolation();
                return false;
            }
        }

        return true;
    }

}
