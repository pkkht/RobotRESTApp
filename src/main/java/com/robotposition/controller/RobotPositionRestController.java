package com.robotposition.controller;

import com.robotposition.model.RobotPositionCommands;
import com.robotposition.model.RobotPosition;
import com.robotposition.service.IRobotPositionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Hari
 * REST API endpoints implemented for supported HTTP methods
 */
@RestController
@RequestMapping("/api/robotposition")
public class RobotPositionRestController {

    @Autowired
    IRobotPositionService robotPositionService;

    @PostMapping("/createRobotPosition")
    public ResponseEntity<Object> createRobotPosition(@Valid @RequestBody @NotNull RobotPosition robotPosition) throws Exception {
        RobotPosition newRobotPosition = robotPositionService.createRobotPosition(robotPosition);
        return new ResponseEntity<>(newRobotPosition, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RobotPosition> getRobotPosition(@PathVariable @NotNull @Valid Integer id) throws Exception{
            Optional<RobotPosition> robotPosition = robotPositionService.findRobotPositionById(id);

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
    public ResponseEntity<Object> updateRobotPosition(@RequestBody RobotPosition robotPosition) throws Exception{
        if (!robotPositionService.findRobotPositionById(robotPosition.getRobotPositionId()).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        RobotPosition updatedRobotPosition = robotPositionService.updateRobotPosition(robotPosition);
        return new ResponseEntity<>(updatedRobotPosition, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRobotPosition(@PathVariable @NotNull Integer id) throws Exception {
        if (!robotPositionService.findRobotPositionById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        robotPositionService.deleteRobotPositionById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
