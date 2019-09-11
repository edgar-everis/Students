package com.domain.main.service;

import com.domain.main.model.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



public interface StudentService {

	Flux<Student> getAll();
	
	Flux<Student> findbyfullname(String fullname);
	
	
    Flux<Student> findbydocument(String document);
    
    Mono<Student> findById(String id);
    
    Mono<Student> createStudent( Student student);
  
     Mono<Student> modifyStudent(String id, Student student);
	
	Mono<Void> deleteById(String id);
	
}
