package com.robotposition.service;

import com.robotposition.model.RobotPosition;
import com.robotposition.model.RobotPositionCommands;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @author Hari
 */
public interface IRobotPositionService {

      RobotPosition createRobotPosition(RobotPosition robotPosition) throws Exception;

      Optional<RobotPosition> findRobotPositionById(Integer id) throws Exception;

      RobotPosition updateRobotPosition(RobotPosition robotPosition) throws Exception;

      void deleteRobotPositionById(Integer id) throws Exception;

      List<RobotPosition> findAllRobotPositions();

      Page<RobotPosition> pagination(int offset, int pageSize, String field);
}
