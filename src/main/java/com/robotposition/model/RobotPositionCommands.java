package com.robotposition.model;

import com.robotposition.validation.ValidCommand;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class RobotPositionCommands {

    @Valid
    private Long robotPositionId;

    @ValidCommand
    private String robotPositionCommands;
}
