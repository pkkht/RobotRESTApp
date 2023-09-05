package com.codingchallenge.helper;

import com.codingchallenge.model.RobotPosition;
import com.codingchallenge.model.RobotPositionCommands;
import org.springframework.stereotype.Component;
import static com.codingchallenge.model.RobotPositionEnum.WEST;
import static com.codingchallenge.model.RobotPositionEnum.EAST;
import static com.codingchallenge.model.RobotPositionEnum.NORTH;
import static com.codingchallenge.model.RobotPositionEnum.SOUTH;
import static com.codingchallenge.model.RobotPositionEnum.MAX_X;
import static com.codingchallenge.model.RobotPositionEnum.MAX_Y;
import static com.codingchallenge.model.RobotPositionEnum.MIN_X;
import static com.codingchallenge.model.RobotPositionEnum.MIN_Y;
import static com.codingchallenge.model.RobotPositionCommandEnum.TURN_RIGHT;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

@Component
public class RobotCommandsHelper {

    Predicate<RobotPosition> westPredicate = (c)-> c.getFacingdir().equals(WEST.getDirection());
    Predicate<RobotPosition> eastPredicate = (c)-> c.getFacingdir().equals(EAST.getDirection());
    Predicate<RobotPosition> northPredicate = (c)-> c.getFacingdir().equals(NORTH.getDirection());
    Predicate<RobotPosition> southPredicate = (c)-> c.getFacingdir().equals(SOUTH.getDirection());

    Predicate<String> rightTurnPredicate = c -> c.equals(TURN_RIGHT.getCommand());
    BiConsumer<RobotPosition, String> biConsumer = (c,d) -> c.setFacingdir(d);


    public RobotPosition updateRobotPositionBasedOnCommands(RobotPosition currentRobotPosition,
                                                            RobotPositionCommands robotPositionCommands) throws Exception{


        List<String> commands = Arrays.asList(robotPositionCommands.getRobotPositionCommands().split(" "));

        for (String command: commands){
            switch(command){
                case "MOVE" -> handleMove(currentRobotPosition);
                case "LEFT", "RIGHT" -> handleTurn(currentRobotPosition, command);
            }
        }

        return currentRobotPosition;
    }

    private void handleTurn(RobotPosition currentRobotPosition, String command) {

       if(rightTurnPredicate.test(command)) {
           if (westPredicate.test(currentRobotPosition))
               biConsumer.accept(currentRobotPosition, NORTH.getDirection());
           else if (eastPredicate.test(currentRobotPosition)) {
               biConsumer.accept(currentRobotPosition, SOUTH.getDirection());
           } else if (northPredicate.test(currentRobotPosition)) {
               biConsumer.accept(currentRobotPosition, EAST.getDirection());
           } else if (southPredicate.test(currentRobotPosition)) {
               biConsumer.accept(currentRobotPosition, WEST.getDirection());
           }
       } else {/* This else is for LEFT command. If not RIGHT, must be LEFT*/
           if (westPredicate.test(currentRobotPosition))
               biConsumer.accept(currentRobotPosition, SOUTH.getDirection());
           else if (eastPredicate.test(currentRobotPosition)) {
               biConsumer.accept(currentRobotPosition, NORTH.getDirection());
           } else if (northPredicate.test(currentRobotPosition)) {
               biConsumer.accept(currentRobotPosition, WEST.getDirection());
           } else if (southPredicate.test(currentRobotPosition)) {
               biConsumer.accept(currentRobotPosition, EAST.getDirection());
           }
       }
    }

    private void handleMove(RobotPosition currentRobotPosition) throws Exception {

        if (westPredicate.test(currentRobotPosition)){
            handleWestMove(currentRobotPosition);
        } else if (eastPredicate.test(currentRobotPosition)){
            handleEastMove(currentRobotPosition);
        } else if (northPredicate.test(currentRobotPosition)){
            handleNorthMove(currentRobotPosition);
        } else if (southPredicate.test(currentRobotPosition)){
            handleSouthMove(currentRobotPosition);
        }

    }
    private void handleNorthMove(RobotPosition currentRobotPosition) throws Exception {
        if (currentRobotPosition.getYpos() == MAX_Y.getValue()){
            throw new Exception("Illegal move in the north direction. The robot cannot fall off the table.");
        }
        else {
            currentRobotPosition.setYpos(currentRobotPosition.getYpos()+1);
        }
    }

    private void handleSouthMove(RobotPosition currentRobotPosition) throws Exception {
        if (currentRobotPosition.getYpos() == MIN_Y.getValue()){
            throw new Exception("Illegal move in the south direction. The robot cannot fall off the table.");
        }
        else {
            currentRobotPosition.setXpos(currentRobotPosition.getYpos()-1);
        }
    }
    private void handleEastMove(RobotPosition currentRobotPosition) throws Exception {
        if (currentRobotPosition.getXpos() == MAX_X.getValue()){
            throw new Exception("Illegal move in the east direction. The robot cannot fall off the table.");
        }
        else {
            currentRobotPosition.setXpos(currentRobotPosition.getXpos()+1);
        }
    }

    private void handleWestMove(RobotPosition currentRobotPosition) throws Exception {
        if (currentRobotPosition.getXpos() == MIN_X.getValue()){
            throw new Exception("Illegal move in the west direction. The robot cannot fall off the table.");
        }
        else {
            currentRobotPosition.setXpos(currentRobotPosition.getXpos()-1);
        }
    }
}
