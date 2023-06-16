package com.demo.helloSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.helloSpring.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
