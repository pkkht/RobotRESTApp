package com.codingchallenge.repository;

import com.codingchallenge.model.RobotPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides default CRUD methods
 */
@Repository
public interface RobotPositionRepository extends JpaRepository<RobotPosition, Long> {

}
