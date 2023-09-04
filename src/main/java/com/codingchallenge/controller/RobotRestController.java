package com.codingchallenge.controller;

import com.codingchallenge.model.Robot;
import com.codingchallenge.service.IRobotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
            return new ResponseEntity<>(robot, HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*API for fetching a robot by its unique ID*/
    /*Request Type: GET */
    /*URL Pattern: /id */
    /*When robot is found for the given id, return the task. Otherwise, 404 */
    @GetMapping("/{id}")
    public ResponseEntity<Robot> getTask(@PathVariable Long id) {
        Optional<Robot> robot= robotService.findRobotById(id);

        if (!robot.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(robot.get());
        }
    }
}
