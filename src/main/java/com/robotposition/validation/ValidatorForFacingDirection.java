package com.robotposition.validation;

import com.robotposition.model.RobotPositionEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author Hari
 * Custom validator for RobotPosition.FacingDirection field
 */
public class ValidatorForFacingDirection implements ConstraintValidator<ValidFacingDirection, String> {

    @Override
    public boolean isValid(String facingDir, ConstraintValidatorContext constraintValidatorContext) {
         return RobotPositionEnum.streamRobotPositionDirections().anyMatch(dir -> dir.getDirection().equals(facingDir));
    }
}
