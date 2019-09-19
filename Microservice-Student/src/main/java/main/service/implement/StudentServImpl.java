package main.service.implement;



import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.Student;
import main.repository.StudentRepository;
import main.service.StudentService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class StudentServImpl implements StudentService{

@Autowired
private StudentRepository studentrepository;

@Override
public Flux<Student> getAll() {return studentrepository.findAll();}

@Override
public Mono<Student> findByFullname(String fullname) {return studentrepository.findByFullname(fullname);}

@Override
public Mono<Student> findByDocument(String document){return studentrepository.findByDocument(document);}

@Override
public Mono<Student> createStudent(Student student){return studentrepository.save(student);}

@Override
public Mono<Student> modifyStudent(String id, Student student) {student.setId(id); return studentrepository.save(student);}

@Override
public Mono<Void> deleteById(String id) {return studentrepository.deleteById(id);}



@Override
public Mono<Student> findbyId(String id) {return studentrepository.findById(id);
}

@Override
public Flux<Student> findByBirthdayBetween(LocalDate from, LocalDate to) {
	
	System.out.println("paso el servicio");
	return studentrepository.findByBirthdayBetween(from, to);
}
}
