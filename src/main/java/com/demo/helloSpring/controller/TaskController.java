package com.demo.helloSpring.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.helloSpring.model.Task;
import com.demo.helloSpring.service.TaskService;

import jakarta.annotation.PostConstruct;

@RestController
public class TaskController {

	@Autowired
	private TaskService service;

	@PostConstruct
	public void initTasks() {
		List<Task> task = Arrays.asList(
				new Task("make todo list", "add relevant details from next day", " pending - open", "Not Assigned"),
				new Task("coding", "create backend app", "finished", "Not Assinged"));
		service.createTasks(task);

	}

	@GetMapping("/tasks")
	public List<Task> getAllTasks() {
		return service.getAllTasks();
	}

	@GetMapping("/tasks/name/{assignedTo}")
	public List<Task> getTaskByName(@PathVariable String assignedTo) {
		return service.getTaskByName(assignedTo);
	}

	@GetMapping("/tasks/{id}")
	public Task getTaskById(@PathVariable Long id) {
		return service.getTaskById(id);
	}

	@PostMapping("/tasks")
	public Task createTask(@RequestBody Task task) {
		return service.createTask(task);
	}

	@PutMapping("/tasks/{id}")
	public Task updateTask(@RequestBody Task task, @PathVariable Long id) {
		return service.updateTask(id, task);
	}

	@PatchMapping("/tasks/{id}")
	public Task partialUpdate(@RequestBody Map<String, Object> fields, @PathVariable Long id) {
		return service.patch(id, fields);
	}

	@DeleteMapping("/tasks/{id}")
	public void deleteTask(@PathVariable Long id) {
		service.deleteTask(id);

	}

}
