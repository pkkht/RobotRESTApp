package com.codingchallenge.model;

import com.codingchallenge.validation.ValidFacingDirection;
import com.codingchallenge.validation.ValidXPos;
import com.codingchallenge.validation.ValidYPos;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "robot_position")
@Data
public class RobotPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

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
