package com.robotposition.service;

import com.robotposition.exception.DuplicateRobotPositionException;
import com.robotposition.helper.RobotCommandsHelper;
import com.robotposition.model.RobotPosition;
import com.robotposition.model.RobotPositionCommands;
import com.robotposition.repository.RobotPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public RobotPosition createRobotPosition(RobotPosition robotPosition)  {
        RobotPosition newRobotPosition;
        try {
            newRobotPosition = robotPositionRepository.save(robotPosition);
            if (newRobotPosition.getRobotPositionId() > 0) {
                return newRobotPosition;
            }
        }
        catch (DataIntegrityViolationException e){
            throw new DuplicateRobotPositionException(e);
        }
        catch(Exception e){
            throw e;
        }
        return newRobotPosition;
    }

    @Override
    public Optional<RobotPosition> findRobotPositionById(Integer id) {
            return robotPositionRepository.findById(id);
    }

    /**
     * @param robotPositionCommands
     * @return
     */
    @Override
    public RobotPosition updateRobotPosition(RobotPositionCommands robotPositionCommands) throws Exception  {

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
    public void deleteRobotPositionById(Integer id) throws Exception {
        robotPositionRepository.deleteById(id);
    }

    @Override
    public List<RobotPosition> findAllRobotPositions() {
        return robotPositionRepository.findAll();
    }
}
