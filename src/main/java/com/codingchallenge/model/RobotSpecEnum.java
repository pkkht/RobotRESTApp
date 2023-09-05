package com.codingchallenge.model;

import java.util.HashMap;
import java.util.Map;

public enum RobotSpecEnum {

    MAX_X(4),
    MIN_X(0),

    MAX_Y(4),
    MIN_Y(0),

    NORTH("N"),
    SOUTH("S"),

    EAST("E"),
    WEST("W");


    private static Map map = new HashMap<>();
    static {
        for (RobotSpecEnum robotSpec : RobotSpecEnum.values()) {
            map.put(robotSpec, robotSpec.value);
        }
    }

    private String direction;
    RobotSpecEnum(String n) {
        this.direction = n;
    }
    public String getDirection(){
        return this.direction;
    }

    private int value;
    RobotSpecEnum(int i) {
        this.value = i;
    }

    public int getValue(){
        return this.value;
    }

    public static RobotSpecEnum valueOf(int robotSpec) {
        return (RobotSpecEnum) map.get(robotSpec);
    }


}
