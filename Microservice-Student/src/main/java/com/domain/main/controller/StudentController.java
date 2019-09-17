package com.domain.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.domain.main.model.Student;

import com.domain.main.service.StudentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/Student")
public class StudentController {

	
	private final  StudentService stuservice;
	
	@Autowired 
	public StudentController( final StudentService stuservice) {
		// TODO Auto-generated constructor stub
	this.stuservice = stuservice;
		
	}

	//Lista todos los estudiantes
	//@RequestMapping("/Student")
	@GetMapping
	public Flux<Student> getall()
	{
		return stuservice.getAll();
	}
	
	//Lista los estudiantes por nombre
	@GetMapping("/fullname/{fullname}")
	public Mono<Student> findbyFullname(@PathVariable String fullname)
	{
		return stuservice.findByFullname(fullname);
	}
	
	//lista los estudiantes por documento
	@GetMapping("/document/{number}")
	public Mono <Student> findbyDocument(@PathVariable String number)
	{
		return stuservice.findByDocument(number);
	}
	
	//Crea un nuevo estudiante
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Student> createStudent(@RequestBody Student student)
	{
		return stuservice.createStudent(student);
		
	}
	
	//Actualiza un estudiante
	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Student> updateStudent(@PathVariable String id,@RequestBody Student student)
	{
	return stuservice.modifyStudent(id, student);
		}
	
	//Elimina un estudiante
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> deleteStudents(@PathVariable String id) {
			
			return stuservice.deleteById(id);
		}
}
