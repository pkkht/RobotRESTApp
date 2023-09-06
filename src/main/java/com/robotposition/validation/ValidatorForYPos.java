package com.robotposition.validation;

import com.robotposition.model.RobotPositionEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.temporal.ValueRange;

public class ValidatorForYPos implements ConstraintValidator<ValidYPos, Integer> {


    /**
     * @param yValue
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(Integer yValue, ConstraintValidatorContext constraintValidatorContext) {
        final ValueRange range = ValueRange.of(RobotPositionEnum.MIN_Y.getValue(), RobotPositionEnum.MAX_Y.getValue());
        return range.isValidValue(yValue);
    }
}
