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
	public Mono <Student> findByFullname(String fullname) {
		// TODO Auto-generated method stub
		
		return studentrepository.findByFullname(fullname);
	}

	@Override
	public Mono<Student> findByDocument(String document) {
		// TODO Auto-generated method stub
		return studentrepository.findByDocument(document);
	}

	/*@Override
	public Mono<Student> findById(String id) {
		// TODO Auto-generated method stub
		return studentrepository.findById(id);
	}*/

	@Override
	public Mono<Student> createStudent(Student student) {
		// TODO Auto-generated method stub
		return studentrepository.save(student);
	}

	@Override
	public Mono<Student> modifyStudent(String id, Student student) {
		// TODO Auto-generated method stub
		student.setId(id);
		return studentrepository.save(student);
	}

	@Override
	public Mono<Void> deleteById(String id) {
		// TODO Auto-generated method stub
		return studentrepository.deleteById(id);
	}

	

	
}
