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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.domain.main.model.Student;

import com.domain.main.service.StudentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class StudentController {

	@Autowired 
	private StudentService stuservice;
	
	//Lista todos los estudiantes
	@RequestMapping("/Student")
	public Flux<Student> getall()
	{
		return stuservice.getAll();
	}
	
	//Lista los estudiantes por nombre
	@GetMapping("/Student/fullname")
	public Flux <Student> findbyFullname(@RequestParam("filter") String filter)
	{
		return stuservice.findbyfullname(filter);
	}
	
	//lista los estudiantes por documento
	@GetMapping("/Student/document")
	public Flux <Student> findbyDocument(@RequestParam("number") String number)
	{
		return stuservice.findbydocument(number);
	}
	
	//Crea un nuevo estudiante
	@PostMapping("/Student/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Student> createStudent(@RequestBody Student student)
	{
		return stuservice.createStudent(student);
		
	}
	
	//Actualiza un estudiante
	@PutMapping("/Student/update/{id}")
	public Mono<Student> updateStudent(@PathVariable String id,@RequestBody Student student)
	{
	return stuservice.modifyStudent(id, student);
		}
	
	//Elimina un estudiante
	@DeleteMapping("/Student/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> deleteStudents(@PathVariable String id) {
			
			return stuservice.deleteById(id);
		}
}
