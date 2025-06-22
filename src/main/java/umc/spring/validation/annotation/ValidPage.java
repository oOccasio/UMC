package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.ValidPageValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidPageValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPage {

    String message() default "유효한 page 번호를 입력하세요";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
