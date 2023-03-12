package com.devwinter.memberservice.adapter.input.api.valid;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Constraint(validatedBy = Nickname.NickNameValidator.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
public @interface Nickname {

    String message() default "닉네임은 2~10글자 사이로 입력해주세요.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};

    class NickNameValidator implements ConstraintValidator<Nickname, String> {

        @Override
        public boolean isValid(String nickName, ConstraintValidatorContext constraintValidatorContext) {
            if((nickName == null || "".equals(nickName))) {
                return false;
            } else {
                return nickName.length() >= 2 && nickName.length() <= 10;
            }
        }
    }

}