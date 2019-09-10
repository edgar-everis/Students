package com.domain.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.domain.main.model.Student;

import com.domain.main.service.StudentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController

public class StudentController {

	@Autowired 
	private StudentService stuservice;
	
	@RequestMapping("/Student")
	//@GetMapping
	public Flux<Student> getall()
	{
		return stuservice.getAll();
	}
	
	@GetMapping("/Student/fullname")
	public Mono<Student> findbyFullname(@RequestParam("filter") String filter)
	{
		return stuservice.findbyfullname(filter);
	}
}
