package com.robotposition.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidatorForXPos.class)
@Documented
public @interface ValidXPos{

    String message() default "Invalid X Position of the robot - Must be between 0 and 4";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
