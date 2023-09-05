package com.codingchallenge.validation;

import com.codingchallenge.model.RobotPositionEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidatorForFacingDirection implements ConstraintValidator<ValidFacingDirection, String> {


    /**
     * @param facingDir
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String facingDir, ConstraintValidatorContext constraintValidatorContext) {
         return RobotPositionEnum.streamRobotPositionDirections().filter(dir -> dir.getDirection().equals(facingDir)).findAny().isPresent();
    }
}
