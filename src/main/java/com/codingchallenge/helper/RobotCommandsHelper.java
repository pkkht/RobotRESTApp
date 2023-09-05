package com.codingchallenge.helper;

import com.codingchallenge.model.RobotPosition;
import com.codingchallenge.model.RobotPositionCommandEnum;
import com.codingchallenge.model.RobotPositionCommands;
import com.codingchallenge.model.RobotPositionEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class RobotCommandsHelper {

    public RobotPosition updateRobotPositionBasedOnCommands(RobotPosition currentRobotPosition,
                                                            RobotPositionCommands robotPositionCommands) throws Exception{


        List<String> commands = Arrays.asList(robotPositionCommands.getRobotPositionCommands().split(" "));

        for (String command: commands){
            switch(command){
                case "MOVE" -> handleMove(currentRobotPosition);
            }
        }

        return currentRobotPosition;
    }

    private void handleMove(RobotPosition currentRobotPosition) throws Exception {

        if (currentRobotPosition.getFacingdir().equals(RobotPositionEnum.WEST.getDirection())){
            handleWestMove(currentRobotPosition);
        } else if (currentRobotPosition.getFacingdir().equals(RobotPositionEnum.EAST.getDirection())){
            handleEastMove(currentRobotPosition);
        } else if (currentRobotPosition.getFacingdir().equals(RobotPositionEnum.NORTH.getDirection())){
            handleNorthMove(currentRobotPosition);
        } else if (currentRobotPosition.getFacingdir().equals(RobotPositionEnum.SOUTH.getDirection())){
            handleSouthMove(currentRobotPosition);
        }

    }
    private void handleNorthMove(RobotPosition currentRobotPosition) throws Exception {
        if (currentRobotPosition.getYpos() == RobotPositionEnum.MAX_Y.getValue()){
            throw new Exception("Illegal move detected in the north. The robot cannot fall off the table.");
        }
        else {
            currentRobotPosition.setYpos(currentRobotPosition.getYpos()+1);
        }
    }

    private void handleSouthMove(RobotPosition currentRobotPosition) throws Exception {
        if (currentRobotPosition.getYpos() == RobotPositionEnum.MIN_Y.getValue()){
            throw new Exception("Illegal move detected in the south. The robot cannot fall off the table.");
        }
        else {
            currentRobotPosition.setXpos(currentRobotPosition.getYpos()-1);
        }
    }
    private void handleEastMove(RobotPosition currentRobotPosition) throws Exception {
        if (currentRobotPosition.getXpos() == RobotPositionEnum.MAX_X.getValue()){
            throw new Exception("Illegal move detected in the east. The robot cannot fall off the table.");
        }
        else {
            currentRobotPosition.setXpos(currentRobotPosition.getXpos()+1);
        }
    }

    private void handleWestMove(RobotPosition currentRobotPosition) throws Exception {
        if (currentRobotPosition.getXpos() == RobotPositionEnum.MIN_X.getValue()){
            throw new Exception("Illegal move detected in the west. The robot cannot fall off the table.");
        }
        else {
            currentRobotPosition.setXpos(currentRobotPosition.getXpos()-1);
        }
    }
}
