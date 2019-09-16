package com.domain.main.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.domain.main.model.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;




@Repository
public interface StudentRepository extends ReactiveMongoRepository<Student, String> {

	@Query("{ 'fullname' : ?0}")
	Mono<Student> findbyfullname(String fullname);

	
	@Query("{ 'document' : ?0}")
	Flux<Student> findbydocument(String document);
}
