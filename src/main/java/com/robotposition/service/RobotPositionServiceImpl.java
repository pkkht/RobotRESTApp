package com.robotposition.service;

import com.robotposition.exception.DuplicateRobotPositionException;
import com.robotposition.helper.RobotCommandsHelper;
import com.robotposition.model.RobotPosition;
import com.robotposition.model.RobotPositionCommands;
import com.robotposition.repository.RobotPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Hari
 * Service layer methods invoked by the controller layer.
 * These service methods invoke the data layer (repository) to perform the CRUD operations.
 */
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
     * @param robotPosition
     * @return
     */
    @Override
    public RobotPosition updateRobotPosition(RobotPosition robotPosition) throws Exception  {

        try {
            if (robotPositionRepository.existsById(robotPosition.getRobotPositionId())) {
                Optional<RobotPosition> currentRobotPosition = robotPositionRepository.findById(robotPosition.getRobotPositionId());
                if (currentRobotPosition.isPresent()) {
                    RobotPosition updatedRobotPosition = robotCommandsHelper.
                            updateRobotPositionBasedOnCommands(currentRobotPosition.get(), robotPosition.getRobotPositionCommands());
                    updatedRobotPosition = robotPositionRepository.save(updatedRobotPosition);
                    return updatedRobotPosition;
                }
            }
        }  catch (DataIntegrityViolationException e){
            throw new DuplicateRobotPositionException(e);
        }
        catch(Exception e){
            throw e;
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


    @Override
    public Page<RobotPosition> pagination(int offset, int pageSize, String field) {

        if ("defaultValue".equals(field)){
            return robotPositionRepository.findAll(
                    PageRequest.of(offset, pageSize)
            );
        }
        return robotPositionRepository.findAll(
                PageRequest.of(offset, pageSize).withSort(Sort.by(field))
        );
    }
}
