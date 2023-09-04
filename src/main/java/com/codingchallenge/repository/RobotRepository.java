package com.codingchallenge.repository;

import com.codingchallenge.model.Robot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides default CRUD methods
 */
@Repository
public interface RobotRepository extends JpaRepository<Robot, Long> {

}
