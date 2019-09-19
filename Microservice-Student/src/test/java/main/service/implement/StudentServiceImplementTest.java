package main.service.implement;


import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.springframework.test.context.junit4.SpringRunner;

import main.model.Student;
import main.repository.StudentRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.reactivestreams.Publisher;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class StudentServiceImplementTest {

    @Mock
    private StudentRepository studentRepository;
    
    @InjectMocks
    private StudentServImpl studentService;
    
   
    
    @Test
    public void getAll() {
    	LocalDate date = LocalDate.parse("2018-05-05");
        Student student = new Student();
       student.setId("1");
       student.setFullname("Juan");
       student.setGender("m");
       student.setBirthday(date);
       student.setDocument("55555");
       student.setType_doc("dni");
        when(studentService.getAll()).thenReturn(Flux.just(student));
        Flux<Student> actual = studentService.getAll();
        assertResults(actual, student);
    }
    
    @Test
    public void findFullName() {
    	LocalDate date = LocalDate.parse("2018-05-05");
        Student student = new Student();
       student.setId("2");
       student.setFullname("Marcos");
       student.setGender("M");
       student.setBirthday(date);
       student.setDocument("666666");
       student.setType_doc("dni");
      
        when(studentRepository.findByFullname("Marcos")).thenReturn(Mono.just(student));
        Mono<Student> actual = studentService.findByFullname("Marcos");
        assertResults(actual,student);
        System.out.println(actual);
        System.out.println(student.getFullname());
    }
    
    @Test
    public void save() {
    	LocalDate date = LocalDate.parse("2018-05-05");
        Student student = new Student();
        student.setId("2");
        student.setFullname("Marcos");
        student.setGender("M");
        student.setBirthday(date);
        student.setDocument("666666");
        student.setType_doc("dni");
        when(studentRepository.save(student)).thenReturn(Mono.just(student));
        Mono<Student> actual = studentService.createStudent(student);
        assertResults(actual, student);
    }
   
    
  
    @Test
    public void delete() {
    	LocalDate date = LocalDate.parse("2018-05-05");
        Student student = new Student();
        student.setId("3");
        student.setFullname("Carlos");
        student.setGender("m");
        student.setBirthday(date);
        student.setType_doc("dni");
        student.setDocument("5555555");
        when(studentRepository.delete(student)).thenReturn(Mono.empty());
    }
    @Test
    public void findByDocument() {
    	LocalDate date = LocalDate.parse("2018-05-05");
    	Student student = new Student();
        student.setId("1");
        student.setFullname("max");
        student.setBirthday(date);
        student.setDocument("123456");
        student.setType_doc("dni");
        student.setGender("m");
      
      
        when(studentRepository.findByDocument("123456")).thenReturn(Mono.just(student));
        Mono<Student> actual = studentService.findByDocument("123456");
        assertResults(actual,student);
    }
    
    private void assertResults(Publisher<Student> publisher, Student... expectedProducts) {
        StepVerifier
            .create(publisher)
            .expectNext(expectedProducts)
            .verifyComplete();

}
}
