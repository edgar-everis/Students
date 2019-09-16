package com.domain.main;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.domain.main.model.Student;
import com.domain.main.repository.StudentRepository;

import reactor.core.publisher.Flux;


@SpringBootTest
@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
public class Stud {

	private StudentRepository repo;
	
	@Before
	public void setUp() throws Exception {
		
		
		
	    	 repo.deleteAll();
	      Flux.just(
	          new Student("1","Pedro","Masculino","05/02/1988","dni","6666"),
	          new Student("2","gonzalo","Masculino","06/03/1990","dni","777"),
	          new Student("3","Jose","Masculino", "02/04/1986","dni" ,"22222"),
	          new Student("4","marcos","Masculino","01/06/1986","dni","55555")
	         )
	        .flatMap(repo::save);
	}

	@Test
	public void testGetall() {
		
	}

}
