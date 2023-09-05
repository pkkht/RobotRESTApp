package com.codingchallenge.validation;

import com.codingchallenge.model.RobotPositionEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.temporal.ValueRange;

public class ValidatorForXPos implements ConstraintValidator<ValidXPos, Integer> {


    /**
     * @param xValue
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(Integer xValue, ConstraintValidatorContext constraintValidatorContext) {
        final ValueRange range = ValueRange.of(RobotPositionEnum.MIN_X.getValue(), RobotPositionEnum.MAX_X.getValue());
        return range.isValidValue(xValue);
    }
}
