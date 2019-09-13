package com.domain.main;
import static org.mockito.Mockito.when;


import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.domain.main.controller.StudentController;
import com.domain.main.model.Student;
import com.domain.main.service.StudentServImpl;

import reactor.core.publisher.Flux;



@ExtendWith(SpringExtension.class)
class StudentControllerTest {

	  @Mock
	  private StudentServImpl studentservice;
	  private WebTestClient client;
	  private List <Student> expectedstudents;
	
	@BeforeEach
	void setUp() throws Exception {
		client = WebTestClient
		        .bindToController(new StudentController(studentservice))
		        .configureClient()
		        .baseUrl("/api/Student")
		        .build();

		    expectedstudents =  Arrays.asList(
		    		Student.builder().id("1").fullname("juan").gender("M").birthday("05/02/1987").Type_doc("dni").document("123456").build(),
		    		Student.builder().id("2").fullname("Juan").gender("M").birthday("06/02/1990").Type_doc("dni").document("666666").build(),
		    		Student.builder().id("3").fullname("Jose").gender("M").birthday("01/02/1992").Type_doc("dni").document("77777").build(),
		    		Student.builder().id("4").fullname("Andres").gender("M").birthday("01/02/1998").Type_doc("dni").document("888888").build(),
		    		Student.builder().id("5").fullname("Pepe").gender("M").birthday("01/02/1996").Type_doc("dni").document("9999999").build()
		    		);
	}

	@Test
	void testGetall() {
		when(studentservice.getAll()).thenReturn(Flux.fromIterable(expectedstudents));

	    client.get().uri("/").exchange()
	        .expectStatus().isOk()
	        .expectBodyList(Student.class).isEqualTo(expectedstudents);
		
	}

	@Test
	void testFindbyFullname() {
		
		  
	        String fullname = "Pedro";	
		    List<Student> expectedFilteredProducts = Arrays.asList(expectedstudents.get(0));
		  
		    when(studentservice.findbyfullname(fullname)).thenReturn(Flux.fromIterable(expectedFilteredProducts));

		    client.get().uri("/fullname/{fullname}", fullname).exchange()
		        .expectStatus().isOk()
		        .expectBodyList(Student.class).isEqualTo(expectedFilteredProducts);
		    System.out.println(expectedstudents.get(0).getFullname());
		
	}

	@Test
	void testFindbyDocument() {
		 
		
	}

	@Test
	void testCreateStudent() {
		
	}

	@Test
	void testUpdateStudent() {
		
	}

	@Test
	void testDeleteStudents() {
		
	}

}
