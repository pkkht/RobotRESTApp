package com.codingchallenge.service;

import com.codingchallenge.model.Robot;

import java.util.Optional;

public interface IRobotService {

      Robot createRobot(Robot robot) ;

       Optional<Robot> findRobotById(Long id);
}
