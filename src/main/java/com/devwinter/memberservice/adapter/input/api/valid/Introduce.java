package com.devwinter.memberservice.adapter.input.api.valid;


import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Constraint(validatedBy = Introduce.IntroduceValidator.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
public @interface Introduce {

    String message() default "자기소개는 5~50글자까지 입력해주세요.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};

    class IntroduceValidator implements ConstraintValidator<Introduce, String> {

        @Override
        public boolean isValid(String introduce, ConstraintValidatorContext constraintValidatorContext) {
            if((introduce == null || "".equals(introduce))) {
                return false;
            } else {
                return introduce.length() >= 5 && introduce.length() <= 50;
            }
        }
    }

}