package com.robotposition.validation;

import com.robotposition.model.RobotPositionCommandEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class ValidatorForCommand implements ConstraintValidator<ValidCommand, String> {


    /**
     * @param commands
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String commands, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.nonNull(commands)) {
            List<String> eachCommand = Arrays.asList(commands.split(" "));
            List<String> robotCommands = RobotPositionCommandEnum.streamRobotCommands().toList();
            return robotCommands.containsAll(eachCommand);
        }
        return true;
    }
}
