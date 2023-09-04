package com.codingchallenge.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "robot")
@Data
public class Robot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="Xpos", nullable = false)
    private int Xpos;

    @Column(name="Ypos", nullable = false)
    private int Ypos;

    @Column(name="facingdir", nullable = false)
    private String facingdir;
}
