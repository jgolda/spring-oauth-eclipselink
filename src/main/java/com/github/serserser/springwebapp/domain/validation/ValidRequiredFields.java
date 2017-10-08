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
@Target(ElementType.TYPE)
@Constraint(validatedBy = ValidRequiredFields.UserRequiredFieldsValidator.class)
public @interface ValidRequiredFields {

    class UserRequiredFieldsValidator implements ConstraintValidator<ValidRequiredFields, User> {

        @Override
        public void initialize(ValidRequiredFields constraintAnnotation) {

        }

        @Override
        public boolean isValid(User value, ConstraintValidatorContext context) {
            return StringUtils.isNotEmpty(value.getLogin()) || StringUtils.isNotEmpty(value.getEmail());
        }
    }

    String message() default "{errors.validation.required.loginOrEmail}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
