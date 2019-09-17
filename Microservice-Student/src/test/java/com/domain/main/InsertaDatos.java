package com.domain.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.domain.main.model.Student;
import com.domain.main.repository.StudentRepository;

import reactor.core.publisher.Flux;

//@Component
public class InsertaDatos implements CommandLineRunner {

	@Autowired
	private StudentRepository sturepositroy;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	

	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		  
		     sturepositroy.deleteAll();
		      Flux.just(
		          new Student("1","Edgar","M","05/02/1988","dni","6666"),
		          new Student("2","Gonzalo","M","06/03/1990","dni","7777"),
		          new Student("3","Jose","M", "02/04/1986","dni" ,"2222")
		          
		         )
		        .flatMap(sturepositroy::save)
		        .subscribe(Student -> log.info("Student: {}", Student));

		    };
	}


