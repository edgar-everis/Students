package com.domain.main;


import java.time.LocalDate;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import main.model.Student;
import main.repository.StudentRepository;
import reactor.core.publisher.Flux;

@Component
public class InsertaDatos implements CommandLineRunner {

	@Autowired
	private StudentRepository sturepositroy;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	

	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stubs
		/*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		String dateInString = "2013-05-02";
		Date date = formatter.parse(dateInString);*/
		LocalDate date = LocalDate.parse("2018-05-05");

		     sturepositroy.deleteAll();
		      Flux.just(
		    	 new Student("1","Edgar","M", date,"dni","6666"),
		          new Student("2","Gonzalo","M",date,"dni","7777"),
		          new Student("3","Jose","M", date,"dni" ,"2222")
		          
		         )
		        .flatMap(sturepositroy::save)
		        .subscribe(Student -> log.info("Student: {}", Student));

		    };
	}


