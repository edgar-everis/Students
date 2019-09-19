package main.repository;


import java.time.LocalDate;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import main.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;




@Repository
public interface StudentRepository extends ReactiveMongoRepository<Student, String> {

    
Mono<Student> findByFullname(String fullname);

//Flux<Student> findByDateofBirthBetween(Date from, Date to);

Mono <Student> findByDocument(String document);

 Flux<Student>findByBirthdayBetween(LocalDate from, LocalDate to);	

//Flux<Student> findByDateofBirthBetween(Date from, Date to);
}
