package com.devwinter.memberservice.adapter.input.api.valid;


import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

import static java.lang.annotation.ElementType.FIELD;

@Constraint(validatedBy = Password.PasswordValidator.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
public @interface Password {

    String message() default "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};

    class PasswordValidator implements ConstraintValidator<Password, String> {

        private final String REGEX_PASSWORD = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}";
        public Pattern email = Pattern.compile(REGEX_PASSWORD);

        @Override
        public boolean isValid(String password, ConstraintValidatorContext context) {
            if((password == null || "".equals(password))) {
                return false;
            } else {
                return email.matcher(password).matches();
            }
        }
    }
}
