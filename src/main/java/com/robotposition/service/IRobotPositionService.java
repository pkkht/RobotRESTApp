package com.robotposition.service;

import com.robotposition.model.RobotPosition;
import com.robotposition.model.RobotPositionCommands;

import java.util.List;
import java.util.Optional;

public interface IRobotPositionService {

      RobotPosition createRobotPosition(RobotPosition robotPosition) throws Exception;

      Optional<RobotPosition> findRobotPositionById(Integer id) throws Exception;

      RobotPosition updateRobotPosition(RobotPositionCommands robotPositionCommands) throws Exception;

      void deleteRobotPositionById(Integer id) throws Exception;

      List<RobotPosition> findAllRobotPositions();
}
