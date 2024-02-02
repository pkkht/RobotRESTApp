package com.robotposition.validation;

import com.robotposition.model.RobotPositionCommandEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * @author Hari
 * Custom validator for Robot position commands issued through REST PUT API
 */
public class ValidatorForCommand implements ConstraintValidator<ValidCommand, String> {

    @Override
    public boolean isValid(String commands, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.nonNull(commands)) {
            List<String> eachCommand = Arrays.asList(commands.split(" "));
            List<String> robotCommands = RobotPositionCommandEnum.streamRobotCommands().toList();
            return new HashSet<>(robotCommands).containsAll(eachCommand);
        }
        return true;
    }
}
