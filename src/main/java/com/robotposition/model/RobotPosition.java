package com.robotposition.model;

import com.robotposition.validation.ValidFacingDirection;
import com.robotposition.validation.ValidXPos;
import com.robotposition.validation.ValidYPos;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "robot_position", uniqueConstraints = {
@UniqueConstraint(name="UniqueRobotPosition", columnNames = {"Xpos","Ypos"})})
@Data
public class RobotPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int robotPositionId;

    @Column(name="Xpos", nullable = false)
    @ValidXPos
    private int xpos;

    @Column(name="Ypos", nullable = false)
    @ValidYPos
    private int ypos;

    @Column(name="facingdir", nullable = false)
    @ValidFacingDirection
    private String facingdir;

}
