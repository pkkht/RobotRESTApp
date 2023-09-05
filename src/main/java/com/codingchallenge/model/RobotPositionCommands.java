package com.codingchallenge.model;

import com.codingchallenge.validation.ValidCommand;
import lombok.Data;

@Data
public class RobotPositionCommands {

    private Long robotPositionId;

    @ValidCommand
    private String robotPositionCommands;
}
