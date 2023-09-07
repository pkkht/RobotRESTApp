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
@Constraint(validatedBy = ValidatorForCommand.class)
@Documented
public @interface ValidCommand{

    String message() default "Invalid Command(s) - must be one of MOVE, LEFT or RIGHT separated by a space. You can have one or more commands of these to control the robot position.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
