package com.robotposition.service;

import com.robotposition.model.RobotPosition;
import com.robotposition.model.RobotPositionCommands;

import java.util.List;
import java.util.Optional;

public interface IRobotPositionService {

      RobotPosition createRobotPosition(RobotPosition robotPosition);

      Optional<RobotPosition> findRobotPositionById(Long id);

      RobotPosition updateRobotPosition(RobotPositionCommands robotPositionCommands) throws Exception;

      void deleteRobotPositionById(Long id);

      List<RobotPosition> findAllRobotPositions();
}
