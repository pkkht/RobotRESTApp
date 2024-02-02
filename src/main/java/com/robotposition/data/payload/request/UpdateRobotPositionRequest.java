package com.robotposition.data.payload.request;

import com.robotposition.validation.ValidCommand;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateRobotPositionRequest {

    @NotNull
    private Integer robotPositionId;
    @ValidCommand
    @NotNull
    private String robotPositionCommands;
}
