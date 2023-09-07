package com.robotposition.repository;

import com.robotposition.model.RobotPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Hari
 * Provides default CRUD methods
 */
@Repository
public interface RobotPositionRepository extends JpaRepository<RobotPosition, Integer> {

}
