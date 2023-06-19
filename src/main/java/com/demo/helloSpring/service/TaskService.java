package com.demo.helloSpring.service;

import java.util.List;
import java.util.Map;
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

	public Task updateTask(Long id, Task task) {
		// TODO Auto-generated method stub
		Optional<Task> tasktemp = repository.findById(id);
		if (tasktemp.isPresent()) {
			Task updateTask = tasktemp.get();

			updateTask.setTitle(task.getTitle());
			updateTask.setDescription(task.getDescription());
			updateTask.setStatus(task.getStatus());
			updateTask.setAssignedTo(task.getAssignedTo());
			return repository.save(updateTask);
		} else {
			Task createTask = new Task();

			createTask.setTitle(task.getTitle());
			createTask.setDescription(task.getDescription());
			createTask.setStatus(task.getStatus());
			createTask.setAssignedTo(task.getAssignedTo());
			return repository.save(createTask);
		}

	}

	public void deleteTask(Long id) {
		// TODO Auto-generated method stub
		Optional<Task> taskTemp = repository.findById(id);
		if (taskTemp.isPresent()) {
			repository.delete(taskTemp.get());
		} else {
			throw new ResourceNotFoundException(" Record not found with id : " + id);
		}
	}

	public List<Task> getTaskByName(String assignedTo) {
		// TODO Auto-generated method stub
		return repository.findByAssignedTo(assignedTo);
	}

	public Task patch(Long id, Map<String, Object> fields) {
		Optional<Task> taskTemp = repository.findById(id);
		if (taskTemp.isPresent()) {
			Task task = taskTemp.get();
			for (Map.Entry<String, Object> field : fields.entrySet()) {
				String parameter = field.getKey();
				Object value = field.getValue();
				System.out.println(parameter + " " + value);
				switch (parameter) {

				case "title": {
					task.setTitle((String) value);
					break;
				}
				case "status": {
					task.setStatus((String) value);
					break;

				}
				case "description": {
					task.setDescription((String) value);
					break;

				}
				case "assignedTo": {
					task.setAssignedTo((String) value);
					break;
				}

				default:
					throw new IllegalArgumentException("Unexpected value: " + parameter);
				}
			}
			repository.save(task);
			return task;

		} else {
			throw new ResourceNotFoundException("Resource not found wiht given id");
		}

	}

}
