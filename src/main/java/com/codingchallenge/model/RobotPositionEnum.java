package com.codingchallenge.model;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum RobotPositionEnum {

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
        for (RobotPositionEnum robotPosition : RobotPositionEnum.values()) {
            map.put(robotPosition, robotPosition.value);
        }
    }

    private String direction;
    RobotPositionEnum(String n) {
        this.direction = n;
    }
    public String getDirection(){
        return this.direction;
    }

    private int value;
    RobotPositionEnum(int i) {
        this.value = i;
    }

    public int getValue(){
        return this.value;
    }

    public static Stream<RobotPositionEnum> streamRobotPositionDirections() {
        return Stream.of(RobotPositionEnum.NORTH, RobotPositionEnum.SOUTH, RobotPositionEnum.EAST, RobotPositionEnum.WEST);
    }

}
