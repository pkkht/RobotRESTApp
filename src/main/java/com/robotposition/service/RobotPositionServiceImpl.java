package com.robotposition.service;

import com.robotposition.exception.DuplicateRobotPositionException;
import com.robotposition.helper.RobotCommandsHelper;
import com.robotposition.model.RobotPosition;
import com.robotposition.repository.RobotPositionRepository;
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
public class RobotPositionServiceImpl implements IRobotPositionService {

    private final RobotPositionRepository robotPositionRepository;

    private final RobotCommandsHelper robotCommandsHelper;

    public RobotPositionServiceImpl(RobotPositionRepository robotPositionRepository, RobotCommandsHelper robotCommandsHelper) {
        this.robotPositionRepository = robotPositionRepository;
        this.robotCommandsHelper = robotCommandsHelper;
    }

    /**
     * @param robotPosition
     * @return
     */
    @Override
    public RobotPosition createRobotPosition(final RobotPosition robotPosition) {
        RobotPosition newRobotPosition;
        try {
            newRobotPosition = robotPositionRepository.save(robotPosition);
            if (newRobotPosition.getRobotPositionId() > 0) {
                return newRobotPosition;
            }
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateRobotPositionException(e);
        }
        return newRobotPosition;
    }

    @Override
    public Optional<RobotPosition> findRobotPositionById(final Integer id) {
        return robotPositionRepository.findById(id);
    }

    /**
     * @param robotPosition
     * @return
     */
    @Override
    public RobotPosition updateRobotPosition(final RobotPosition robotPosition) {

        try {
            Optional<RobotPosition> currentRobotPosition = robotPositionRepository.findById(robotPosition.getRobotPositionId());
            if (currentRobotPosition.isPresent()) {
                RobotPosition updatedRobotPosition = robotCommandsHelper.
                        updateRobotPositionBasedOnCommands(currentRobotPosition.get(), robotPosition.getRobotPositionCommands());
                updatedRobotPosition = robotPositionRepository.save(updatedRobotPosition);
                return updatedRobotPosition;
            }

        } catch (DataIntegrityViolationException e) {
            throw new DuplicateRobotPositionException(e);
        }
        return new RobotPosition();
    }

    @Override
    public void deleteRobotPositionById(final Integer id) {
        robotPositionRepository.deleteById(id);
    }

    @Override
    public List<RobotPosition> findAllRobotPositions() {
        return robotPositionRepository.findAll();
    }


    @Override
    public Page<RobotPosition> pagination(int offset, int pageSize, String field) {

        if ("defaultValue".equals(field)) {
            return robotPositionRepository.findAll(
                    PageRequest.of(offset, pageSize)
            );
        }
        return robotPositionRepository.findAll(
                PageRequest.of(offset, pageSize).withSort(Sort.by(field))
        );
    }
}
