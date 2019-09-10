package com.domain.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.main.model.Student;
import com.domain.main.repository.StudentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentServImpl implements StudentService{

	@Autowired
	private StudentRepository studentrepository;
	
	@Override
	public Flux<Student> getAll() {
		// TODO Auto-generated method stub
		return studentrepository.findAll();
	}

	@Override
	public Mono <Student> findbyfullname(String fullname) {
		// TODO Auto-generated method stub
		return studentrepository.findbyfullname(fullname);
	}

	

	
}
