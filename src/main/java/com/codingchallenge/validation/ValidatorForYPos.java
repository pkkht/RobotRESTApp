package com.codingchallenge.validation;

import com.codingchallenge.model.RobotSpecEnum;
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
        final ValueRange range = ValueRange.of(RobotSpecEnum.MIN_Y.getValue(), RobotSpecEnum.MAX_Y.getValue());
        return range.isValidValue(yValue);
    }
}
