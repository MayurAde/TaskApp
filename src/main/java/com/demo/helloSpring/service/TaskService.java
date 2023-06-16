package com.demo.helloSpring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.helloSpring.model.Task;
import com.demo.helloSpring.repository.TaskRepository;

import exception.ResourceNotFoundException;

@Service
public class TaskService {

	@Autowired
	private TaskRepository repository;

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

	public Task updateTask(Task task) {
		// TODO Auto-generated method stub
		Optional<Task> tasktemp = repository.findById(task.getId());
		if (tasktemp.isPresent()) {
			Task updateTask = tasktemp.get();
			updateTask.setId(task.getId());
			updateTask.setTitle(task.getTitle());
			updateTask.setDescription(task.getDescription());
			updateTask.setStatus(task.getStatus());
			repository.save(updateTask);
			return updateTask;
		} else {
			throw new ResourceNotFoundException(" Record not found with id : " + task.getId());
		}

	}

}
