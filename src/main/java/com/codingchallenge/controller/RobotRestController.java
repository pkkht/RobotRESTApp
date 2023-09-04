package com.codingchallenge.controller;

import com.codingchallenge.model.Robot;
import com.codingchallenge.service.IRobotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/robot")
public class RobotRestController {

    @Autowired
    IRobotService robotService;

    @PostMapping("/createRobot")
    public ResponseEntity<Robot> addTask(@Valid @RequestBody Robot robot) {
        try {
            Robot newRobot = robotService.createRobot(robot);
            if (newRobot.getId() > 0)
                return new ResponseEntity<>(newRobot, HttpStatus.CREATED);
            else {
                return new ResponseEntity<>(newRobot, HttpStatus.EXPECTATION_FAILED);
            }
        } catch (Exception e) {
            //`logger.error("Error in adding a robot", e);
            return new ResponseEntity<>(robot, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
