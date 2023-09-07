package com.robotposition.model;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author Hari
 */
public enum RobotPositionCommandEnum {

    MOVE_ONE_UNIT("MOVE"),
    TURN_LEFT("LEFT"),
    TURN_RIGHT("RIGHT");

    private String command;
    RobotPositionCommandEnum(String command) {
        this.command = command;
    }

    private static Map map = new HashMap<>();
    static {
        for (RobotPositionCommandEnum robotCommandEnum : RobotPositionCommandEnum.values()) {
            map.put(robotCommandEnum, robotCommandEnum.command);
        }
    }

    public String getCommand(){
        return this.command;
    }


    public static Stream<String> streamRobotCommands() {
        return Stream.of(RobotPositionCommandEnum.MOVE_ONE_UNIT.getCommand(), RobotPositionCommandEnum.TURN_LEFT.getCommand(), RobotPositionCommandEnum.TURN_RIGHT.getCommand());
    }
}

