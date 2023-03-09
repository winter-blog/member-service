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
@Constraint(validatedBy = Email.EmailValidator.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
public @interface Email {

    String message() default "이메일 형식이 맞지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};

    class EmailValidator implements ConstraintValidator<Email, String> {
        private final String REGEX_EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        public Pattern email = Pattern.compile(REGEX_EMAIL);

        @Override
        public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
            if((email == null || "".equals(email))) {
                return false;
            } else {
                return this.email.matcher(email).matches();
            }
        }
    }

}