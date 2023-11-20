package com.task.controller;

import com.task.controller.application.port.out.TaskPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ControllerApplication  {

	@Autowired
	private TaskPort taskRepository;

	public static void main(String[] args) {

		SpringApplication.run(ControllerApplication.class, args);
	}
}
