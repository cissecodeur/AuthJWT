package com.authentification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authentification.dao.TaskRepository;
import com.authentification.entity.Task;

@RestController
@RequestMapping("/task")
public class TaskRestController {
				
	
	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping("/getAll")
	public List<Task> getAllTask() {
		return taskRepository.findAll();
		
	}
	
	@PostMapping("/save")
	public Task saveTask(@RequestBody Task task) {   
		return taskRepository.save(task);
	}

}
