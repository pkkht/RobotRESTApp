package com.robotposition.service;

import com.robotposition.model.RobotPosition;
import com.robotposition.model.RobotPositionCommands;

import java.util.List;
import java.util.Optional;

public interface IRobotPositionService {

      RobotPosition createRobotPosition(RobotPosition robotPosition);

      Optional<RobotPosition> findRobotPositionById(Integer id);

      RobotPosition updateRobotPosition(RobotPositionCommands robotPositionCommands) throws Exception;

      void deleteRobotPositionById(Integer id);

      List<RobotPosition> findAllRobotPositions();
}
