package com.robotposition.model;

import com.robotposition.validation.ValidCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import lombok.Data;

/**
 * @author Hari
 */
@Data
public class RobotPositionCommands {

    @Valid
    @Digits(integer = 5, fraction = 0)
    private Integer robotPositionId;

    @ValidCommand
    private String robotPositionCommands;
}
