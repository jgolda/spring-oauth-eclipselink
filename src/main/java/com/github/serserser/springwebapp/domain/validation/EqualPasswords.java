package com.github.serserser.springwebapp.domain.validation;

import com.github.serserser.springwebapp.domain.User;
import org.apache.commons.lang3.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@Constraint(validatedBy = EqualPasswords.EqualPasswordsValidator.class)
public @interface EqualPasswords {

    class EqualPasswordsValidator implements ConstraintValidator<EqualPasswords, User> {

        @Override
        public void initialize(EqualPasswords constraintAnnotation) {

        }

        @Override
        public boolean isValid(User value, ConstraintValidatorContext context) {
            return StringUtils.equals(value.getPlainPassword(), value.getRetypedPlainPassword());
        }
    }

    String message() default "{errors.validation.password.dontMatch}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
