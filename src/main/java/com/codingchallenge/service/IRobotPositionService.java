package com.codingchallenge.service;

import com.codingchallenge.model.RobotPosition;
import com.codingchallenge.model.RobotPositionCommands;

import java.util.Optional;

public interface IRobotPositionService {

      RobotPosition createRobotPosition(RobotPosition robotPosition) ;

      Optional<RobotPosition> findRobotPositionById(Long id);

      RobotPosition updateRobotPosition(RobotPositionCommands robotPositionCommands) throws Exception;

      void deleteRobotPositionById(Long id);
}
