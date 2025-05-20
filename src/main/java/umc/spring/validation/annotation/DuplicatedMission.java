package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import umc.spring.validation.validator.DuplicatedMissionValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DuplicatedMissionValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DuplicatedMission {

    String message() default "이미 완료한 미션입니다.";
}
