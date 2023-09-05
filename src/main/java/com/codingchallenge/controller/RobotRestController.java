package com.codingchallenge.controller;

import com.codingchallenge.model.Robot;
import com.codingchallenge.service.IRobotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/robot")
public class RobotRestController {

    @Autowired
    IRobotService robotService;

    @PostMapping("/createRobot")
    public ResponseEntity<Object> addRobot(@Valid @RequestBody Robot robot) {
        try {
            Robot newRobot = robotService.createRobot(robot);
            if (newRobot.getId() > 0)
                return new ResponseEntity<>(newRobot, HttpStatus.CREATED);
            else {
                return new ResponseEntity<>(robot, HttpStatus.EXPECTATION_FAILED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /*API for fetching a robot by its unique ID*/
    /*Request Type: GET */
    /*URL Pattern: /id */
    /*When robot is found for the given id, return the task. Otherwise, 404 */
    @GetMapping("/report/{id}")
    public ResponseEntity<Robot> getRobot(@PathVariable Long id) {
        Optional<Robot> robot= robotService.findRobotById(id);

        if (!robot.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(robot.get());
        }
    }

    /*API for deleting a robot*/
    /*Request Type: DELETE */
    /*URL Pattern: /id */
    /*When a task is found in the database, delete the robot and send OK response. If not found, 404 response. Or else, 417 for any other error */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteRobot(@PathVariable Long id) {

        try {
            if (!robotService.findRobotById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            robotService.deleteRobotById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(id, HttpStatus.EXPECTATION_FAILED);
        }

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
