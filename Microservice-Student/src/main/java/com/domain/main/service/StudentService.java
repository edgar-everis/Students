package com.domain.main.service;

import com.domain.main.model.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface StudentService {

	Flux<Student> getAll();
	
	Mono<Student> findbyfullname(String fullname);
	
}
