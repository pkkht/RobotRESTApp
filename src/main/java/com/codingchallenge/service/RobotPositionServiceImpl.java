package com.codingchallenge.service;

import com.codingchallenge.helper.RobotCommandsHelper;
import com.codingchallenge.model.RobotPosition;
import com.codingchallenge.model.RobotPositionCommands;
import com.codingchallenge.repository.RobotPositionRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RobotPositionServiceImpl implements IRobotPositionService{

    @Autowired
    RobotPositionRepository robotPositionRepository;

    @Autowired
    RobotCommandsHelper robotCommandsHelper;

    /**
     * @param robotPosition
     * @return
     */
    @Override
    public RobotPosition createRobotPosition(RobotPosition robotPosition) {
        RobotPosition newRobotPosition = robotPositionRepository.save(robotPosition);
        if(newRobotPosition.getId() > 0){
            return newRobotPosition;
        }
        return newRobotPosition;
    }

    @Override
    public Optional<RobotPosition> findRobotPositionById(Long id) {
        return robotPositionRepository.findById(id);
    }

    /**
     * @param robotPositionCommands
     * @return
     */
    @SneakyThrows
    @Override
    public RobotPosition updateRobotPosition(RobotPositionCommands robotPositionCommands) {

        if (robotPositionRepository.existsById(robotPositionCommands.getRobotPositionId())){
            Optional<RobotPosition> currentRobotPosition = robotPositionRepository.findById(robotPositionCommands.getRobotPositionId());
            if (currentRobotPosition.isPresent()) {
                RobotPosition updatedRobotPosition = robotCommandsHelper.
                        updateRobotPositionBasedOnCommands(currentRobotPosition.get(), robotPositionCommands);
                updatedRobotPosition = robotPositionRepository.save(updatedRobotPosition);
                return updatedRobotPosition;
            }
        }
        return new RobotPosition();
    }

    @Override
    public void deleteRobotPositionById(Long id) {
        robotPositionRepository.deleteById(id);
    }
}
