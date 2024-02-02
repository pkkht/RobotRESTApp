package com.robotposition.service;

import com.robotposition.data.payload.request.CreateRobotPositionRequest;
import com.robotposition.data.payload.request.UpdateRobotPositionRequest;
import com.robotposition.model.RobotPosition;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @author Hari
 */
public interface IRobotPositionService {

      RobotPosition createRobotPosition(CreateRobotPositionRequest robotPositionRequest) throws Exception;

      Optional<RobotPosition> findRobotPositionById(Integer id) throws Exception;

      RobotPosition updateRobotPosition(UpdateRobotPositionRequest request) throws Exception;

      void deleteRobotPositionById(Integer id) throws Exception;

      List<RobotPosition> findAllRobotPositions();

      Page<RobotPosition> pagination(int offset, int pageSize, String field);
}
