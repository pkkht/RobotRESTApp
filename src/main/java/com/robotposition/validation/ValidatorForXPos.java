package com.robotposition.validation;

import com.robotposition.model.RobotPositionEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.temporal.ValueRange;

/**
 * @author Hari
 * Custom validator for RobotPosition.XPos field
 */
public class ValidatorForXPos implements ConstraintValidator<ValidXPos, Integer> {

    @Override
    public boolean isValid(Integer xValue, ConstraintValidatorContext constraintValidatorContext) {
        final ValueRange range = ValueRange.of(RobotPositionEnum.MIN_X.getValue(), RobotPositionEnum.MAX_X.getValue());
        return range.isValidValue(xValue);
    }
}
