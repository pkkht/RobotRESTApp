package com.robotposition.data.payload.request;

import com.robotposition.validation.ValidFacingDirection;
import com.robotposition.validation.ValidXPos;
import com.robotposition.validation.ValidYPos;
import lombok.Data;

@Data
public class CreateRobotPositionRequest {
    @ValidXPos
    private int xpos;

    @ValidYPos
    private int ypos;

    @ValidFacingDirection
    private String facingdir;
}
