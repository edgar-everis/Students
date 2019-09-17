package com.domain.main.controller;

import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.domain.main.model.Student;
import com.domain.main.service.StudentService;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

	@Autowired
	private WebTestClient client;
	
	@Autowired
	private StudentService service;
	
	@Test
	public void testGetall() {
		
		
		client.get().uri("/api/Student")
		.accept(MediaType.APPLICATION_JSON_UTF8)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
		.expectBodyList(Student.class)
		.consumeWith(response -> {
            List<Student> students = response.getResponseBody();
            students.forEach(p -> {
                System.out.println(p.getFullname());
            });

            Assertions.assertThat(students.size()>0).isTrue();
		  });
	}

	@Test
	public void testFindbyFullname() {
		 Student stu = service.findByFullname("Jose").block();
	        client.get()
	                .uri("/api/Student" + "/fullname/{fullname}", Collections.singletonMap("fullname", stu.getFullname()))
	                .accept(MediaType.APPLICATION_JSON_UTF8)
	                .exchange()
	                .expectStatus().isOk()
	                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
	                .expectBody(Student.class)
	                .consumeWith(response -> {
	                    Student s = response.getResponseBody();
	                    Assertions.assertThat(s.getFullname()).isNotEmpty();
	                    Assertions.assertThat(s.getFullname().length()>0).isTrue();

	                });
	}

	@Test
	public void testFindbyDocument() {
		
		Student stu = service.findByDocument("2222").block();
        client.get()
                .uri("/api/Student" + "/document/{number}", Collections.singletonMap("number", stu.getDocument()))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Student.class)
                .consumeWith(response -> {
                    Student s = response.getResponseBody();
                    Assertions.assertThat(s.getDocument()).isNotEmpty();
                    Assertions.assertThat(s.getDocument().length()>0).isTrue();
                    System.out.println(s.getFullname());

                });
	
	}

	
	
	@Test
	public void testCreateStudent() {
		Student stu = new Student("5","Michael","M","02/05/1987","dni","123456");

        client.post().uri("/api/Student/create")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(stu), Student.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
        		.jsonPath("$.id").isNotEmpty()
        		.jsonPath("$.fullname").isEqualTo("Michael");
        		System.out.println(stu.getFullname());
        		
        
        		
		
	}

	@Test
	public void testUpdateStudent() {
		Student student = service.findByFullname("Michael").block();


        Student stuedit = new Student("5", "Jackson","m","05/02/1987","dni","777777");

        client.put().uri("/api/Student" + "/update/{id}", Collections.singletonMap("id", student.getId()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(stuedit),Student.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.fullname").isEqualTo("Jackson")
                ;
	}

	@Test
	public void testDeleteStudents() {
		  Student stu = service.findByFullname("Jackson").block();
	        client.delete()
	                .uri("/api/Student" + "/delete/{id}", Collections.singletonMap("id", stu.getId()))
	                .exchange()
	                .expectStatus().isNoContent()
	                .expectBody()
	                .isEmpty();

	        
	}

}
