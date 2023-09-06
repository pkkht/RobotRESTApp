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
@Constraint(validatedBy = ValidatorForFacingDirection.class)
@Documented
public @interface ValidFacingDirection {

    String message() default "Invalid Facing Direction of the robot - must be one of values N, S, E, W - " +
            "representing North, South, East and West respectively";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
