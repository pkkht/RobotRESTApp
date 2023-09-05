package com.codingchallenge.controller;

import com.codingchallenge.model.RobotPositionCommands;
import com.codingchallenge.model.RobotPosition;
import com.codingchallenge.service.IRobotPositionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
@RequestMapping("/robotposition")
public class RobotPositionRestController {

    @Autowired
    IRobotPositionService robotPositionService;

    @PostMapping("/createRobotPosition")
    public ResponseEntity<Object> createRobotPosition(@Valid @RequestBody @NotNull RobotPosition robotPosition) {
        try {
            RobotPosition newRobotPosition = robotPositionService.createRobotPosition(robotPosition);
            if (newRobotPosition.getId() > 0)
                return new ResponseEntity<>(newRobotPosition, HttpStatus.CREATED);
            else {
                return new ResponseEntity<>(robotPosition, HttpStatus.EXPECTATION_FAILED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /*API for fetching a robot position by its unique ID*/
    /*Request Type: GET */
    /*URL Pattern: /id */
    /*When robot position is found for the given id, return the robot position. Otherwise, 404 */
    @GetMapping("/report/{id}")
    public ResponseEntity<RobotPosition> getRobotPosition(@PathVariable @NotNull Long id) {
        Optional<RobotPosition> robotPosition= robotPositionService.findRobotPositionById(id);

        if (!robotPosition.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(robotPosition.get());
        }
    }

    @PutMapping("/updateRobotPosition")
    public ResponseEntity<Object> updateRobotPosition(@Valid @RequestBody @NotNull RobotPositionCommands robotPositionCommands) {
        try {
            if (!robotPositionService.findRobotPositionById(robotPositionCommands.getRobotPositionId()).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            RobotPosition updatedRobotPosition = robotPositionService.updateRobotPosition(robotPositionCommands);
            return new ResponseEntity<>(updatedRobotPosition, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /*API for deleting a robot position*/
    /*Request Type: DELETE */
    /*URL Pattern: /id */
    /*When a robot position is found in the database, delete the robot position
    and send OK response. If not found, 404 response. Or else, 417 for any other error */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteRobotPosition(@PathVariable @NotNull Long id) {

        try {
            if (!robotPositionService.findRobotPositionById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            robotPositionService.deleteRobotPositionById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(id, HttpStatus.EXPECTATION_FAILED);
        }

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
