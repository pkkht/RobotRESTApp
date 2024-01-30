package com.robotposition.controller;

import com.robotposition.data.payload.request.UpdateRobotPositionRequest;
import com.robotposition.model.RobotPosition;
import com.robotposition.service.IRobotPositionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
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
    private final IRobotPositionService robotPositionService;

    public RobotPositionRestController(final IRobotPositionService robotPositionService) {
        this.robotPositionService = robotPositionService;
    }

    @PostMapping("/createRobotPosition")
    public ResponseEntity<RobotPosition> createRobotPosition(@Valid @RequestBody @NotNull final RobotPosition robotPosition) throws Exception {
        return new ResponseEntity<>( robotPositionService.createRobotPosition(robotPosition), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RobotPosition> getRobotPosition(@PathVariable @NotNull @Valid final Integer id) throws Exception {
        Optional<RobotPosition> robotPosition = robotPositionService.findRobotPositionById(id);
        return robotPosition.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/report")
    public ResponseEntity<List<RobotPosition>> getAllRobotPositions() {
        List<RobotPosition> robotPositions = robotPositionService.findAllRobotPositions();
        return robotPositions.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(robotPositions);
    }

    @PutMapping("/updateRobotPosition")
    public ResponseEntity<Object> updateRobotPosition(@RequestBody @Valid final UpdateRobotPositionRequest request) throws Exception {
       final RobotPosition updatedRobotPosition = robotPositionService.updateRobotPosition(request);
       return updatedRobotPosition.getRobotPositionId() != 0 ? ResponseEntity.ok(updatedRobotPosition) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRobotPosition(@PathVariable @NotNull final Integer id) throws Exception {
        if (robotPositionService.findRobotPositionById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        robotPositionService.deleteRobotPositionById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Implement pagination with sorting
    @GetMapping("/pagination/{offset}/{pageSize}")
    public ResponseEntity<Page<RobotPosition>> pagination(
            @PathVariable int offset,
            @PathVariable int pageSize,
            @RequestParam(required = false, defaultValue = "defaultValue") String field) {
        Page<RobotPosition> robotPositions = robotPositionService.pagination(offset, pageSize, field);

        if (robotPositions.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(robotPositions);
        }
    }
}
