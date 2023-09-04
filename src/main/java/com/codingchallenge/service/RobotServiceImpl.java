package com.codingchallenge.service;

import com.codingchallenge.model.Robot;
import com.codingchallenge.repository.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RobotServiceImpl implements IRobotService{

    @Autowired
    RobotRepository robotRepository;

    /**
     * @param robot
     * @return
     */
    @Override
    public Robot createRobot(Robot robot) {
        Robot newRobot = robotRepository.save(robot);
        if(newRobot.getId() > 0){
            return newRobot;
        }
        return robot;
    }

    @Override
    public Optional<Robot> findRobotById(Long id) {
        return robotRepository.findById(id);
    }
}
