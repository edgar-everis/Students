package main.service;



import java.time.LocalDate;
import java.util.Date;

import main.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
Flux<Student> getAll();

Mono<Student> findByFullname(String fullname);

Mono<Student> findByDocument(String document); 

Flux<Student>findByBirthdayBetween(LocalDate from, LocalDate to);	

Mono<Student> createStudent( Student student);

Mono<Student> modifyStudent(String id, Student student);

Mono<Void> deleteById(String id);

Mono<Student> findbyId(String id);
}
