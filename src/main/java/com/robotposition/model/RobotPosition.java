package com.robotposition.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.robotposition.validation.ValidCommand;
import com.robotposition.validation.ValidFacingDirection;
import com.robotposition.validation.ValidXPos;
import com.robotposition.validation.ValidYPos;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Transient;

/**
 * @author Hari
 */
@Entity
@Table(name = "robot_position", uniqueConstraints = {
@UniqueConstraint(name="UniqueRobotPosition", columnNames = {"Xpos","Ypos"})})
@JsonIgnoreProperties(ignoreUnknown = true)
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

    @ValidCommand
    @Transient
    @JsonIgnore
    private String robotPositionCommands;

}
