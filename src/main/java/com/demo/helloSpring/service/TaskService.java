package com.demo.helloSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.helloSpring.model.Task;
import com.demo.helloSpring.repository.TaskRepository;

import jakarta.annotation.PostConstruct;

@Service
public class TaskService {

	@Autowired
	private TaskRepository repository;

	@PostConstruct
	public List<Task> getAllTasks() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public Task getTaskById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}

	public Task createTask(Task task) {
		// TODO Auto-generated method stub
		return repository.save(task);
	}

	public List<Task> createTasks(List<Task> tasks) {
		return repository.saveAll(tasks);

	}

}
