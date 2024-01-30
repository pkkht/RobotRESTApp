package com.robotposition.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.robotposition.validation.ValidFacingDirection;
import com.robotposition.validation.ValidXPos;
import com.robotposition.validation.ValidYPos;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

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
}
