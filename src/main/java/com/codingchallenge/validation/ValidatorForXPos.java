package com.codingchallenge.validation;

import com.codingchallenge.model.RobotSpecEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.constraintvalidators.bv.AssertTrueValidator;
import org.springframework.util.Assert;

import java.time.temporal.ValueRange;

public class ValidatorForXPos implements ConstraintValidator<ValidXPos, Integer> {


    /**
     * @param xValue
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(Integer xValue, ConstraintValidatorContext constraintValidatorContext) {
        final ValueRange range = ValueRange.of(RobotSpecEnum.MIN_X.getValue(), RobotSpecEnum.MAX_X.getValue());
        return range.isValidValue(xValue);
    }
}
