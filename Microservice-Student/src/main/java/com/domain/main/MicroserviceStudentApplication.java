package com.domain.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.domain.main.model.Student;
import com.domain.main.repository.StudentRepository;
import reactor.core.publisher.Flux;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

@EnableSwagger2WebFlux//agregar esta anotacion para habilitar el swagger
@SpringBootApplication
public class MicroserviceStudentApplication {
	
	@Autowired
	private StudentRepository sturepositroy;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	 
	
	@Bean
	  CommandLineRunner start(StudentRepository sturepository){
		
	    return args -> {
	    	 sturepositroy.deleteAll();
	      Flux.just(
	          new Student("1","edgar","Masculino","05/02/1988","dni","6666"),
	          new Student("2","gonzalo","Masculino","06/03/1990","dni","777"),
	          new Student("3","Jose","Masculino", "02/04/1986","dni" ,"22222"),
	          new Student("4","marcos","Masculino","01/06/1986","dni","55555")
	         )
	        .flatMap(sturepository::save)
	        .subscribe(Student -> log.info("Student: {}", Student));

	    };
	  }
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceStudentApplication.class, args);
	}

}