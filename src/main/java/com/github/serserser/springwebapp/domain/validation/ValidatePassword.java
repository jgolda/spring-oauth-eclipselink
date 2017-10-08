package com.github.serserser.springwebapp.domain.validation;


import com.oracle.jrockit.jfr.UseConstantPool;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidatePassword.PasswordValidator.class)
public @interface ValidatePassword {

    class PasswordValidator implements ConstraintValidator<ValidatePassword, String> {

        private static final String PATTERN_STRING = "[a-zA-z0-9]*";

        private Pattern pattern;

        @Override
        public void initialize(ValidatePassword constraintAnnotation) {
            pattern = Pattern.compile(PATTERN_STRING);
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            Matcher matcher = pattern.matcher(value);
            return matcher.matches();
        }
    }

    String message() default "{errors.validation.password.wrongPattern}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
