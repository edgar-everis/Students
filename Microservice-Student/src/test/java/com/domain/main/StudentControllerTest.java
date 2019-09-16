package com.domain.main;



import static org.junit.Assert.assertEquals;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.domain.main.model.Student;
import com.domain.main.repository.StudentRepository;

import ch.qos.logback.classic.Logger;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
public class StudentControllerTest {

	@Autowired
    private WebTestClient webTestClient;
	
	/*@Autowired
    private StudentRepository repo;

    private final Student one = new Student("1", "Edgar12","M","12/05/1987","dni","123456");
    private final Student two = new Student("2", "Gonzalo","M","12/05/1987","dni","999999");
    private final Student three = new Student("3", "marco","M","12/05/1987","dni","666666");*/
	private StudentRepository sturepositroy;
	
	 
	
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
	        .flatMap(sturepository::save);
	       

	    };
	 }
	
	@Test
	public void testGetall() {
		  webTestClient.get().uri("/api/Student")
          .accept(MediaType.APPLICATION_JSON_UTF8)
          .exchange()
          .expectStatus().isOk()
          .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
          .expectBodyList(Student.class)
          .hasSize(2);
	}

	
	@Test
	public void testFindbyFullname() {
		 Student expectedValue = new Student("1", "Carlos","M", "02/05/1987", "dni", "123456");

	        webTestClient.get().uri("/fullname/Gonzalo")
	                .accept(MediaType.APPLICATION_JSON_UTF8)
	                .exchange()
	                .expectStatus().isOk()
	                .expectBody(Student.class)
	                .consumeWith((response) -> {
	                    assertEquals(expectedValue, response.getResponseBody());
	                });

	}

	@Test
	public void testFindbyDocument() {
		
	}

	@Test
	public void testCreateStudent() {
		 
	}

	@Test
	public void testUpdateStudent() {
		
	}

	@Test
	public void testDeleteStudents() {
		
	}

}
