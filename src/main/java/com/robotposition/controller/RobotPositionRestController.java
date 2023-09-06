package com.robotposition.controller;

import com.robotposition.model.RobotPositionCommands;
import com.robotposition.model.RobotPosition;
import com.robotposition.service.IRobotPositionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/robotposition")
public class RobotPositionRestController {

    @Autowired
    IRobotPositionService robotPositionService;

    @PostMapping("/createRobotPosition")
    public ResponseEntity<Object> createRobotPosition(@Valid @RequestBody @NotNull RobotPosition robotPosition)  {
        RobotPosition newRobotPosition = robotPositionService.createRobotPosition(robotPosition);
        return new ResponseEntity<>(newRobotPosition, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RobotPosition> getRobotPosition(@PathVariable @NotNull Integer id) {
        Optional<RobotPosition> robotPosition= robotPositionService.findRobotPositionById(id);

        if (!robotPosition.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(robotPosition.get());
        }
    }

    @GetMapping("/report")
    public ResponseEntity<List<RobotPosition>> getAllRobotPositions() {
        List<RobotPosition> robotPositions= robotPositionService.findAllRobotPositions();

        if (robotPositions.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(robotPositions);
        }
    }

    @PutMapping("/updateRobotPosition")
    public ResponseEntity<Object> updateRobotPosition(@Valid @RequestBody @NotNull RobotPositionCommands robotPositionCommands) throws Exception{
        if (!robotPositionService.findRobotPositionById(robotPositionCommands.getRobotPositionId()).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        RobotPosition updatedRobotPosition = robotPositionService.updateRobotPosition(robotPositionCommands);
        return new ResponseEntity<>(updatedRobotPosition, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRobotPosition(@PathVariable @NotNull Integer id) {
        if (!robotPositionService.findRobotPositionById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        robotPositionService.deleteRobotPositionById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
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
