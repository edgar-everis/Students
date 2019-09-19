package main.dummydata;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse("15/08/1980",fmt);
		LocalDate date2 = LocalDate.parse("15/08/1970",fmt);
		LocalDate date3 = LocalDate.parse("15/08/1960",fmt);
		LocalDate date4 = LocalDate.parse("15/08/1990",fmt);
		//LocalDate ahora = LocalDate.now();

		/*Period periodo = Period.between(fechaNac, ahora);
		System.out.printf("Tu edad es: %s años, %s meses y %s días",
		                    periodo.getYears(), periodo.getMonths(), periodo.getDays());
		
		/*LocalDate date = LocalDate.parse("1987-05-02");
		LocalDate date2 = LocalDate.parse("1990-02-05");
		LocalDate date3 = LocalDate.parse("1970-03-20");
		LocalDate date4 = LocalDate.parse("1960-04-15");*/
		     sturepositroy.deleteAll();
		      Flux.just(
		    	 new Student("1","Edgar","M", date,"dni","6666"),
		          new Student("2","Gonzalo","M",date2,"dni","7777"),
		          new Student("3","Jose","M", date3,"dni" ,"2222"),
		          new Student("4","Pedro","M", date4,"dni" ,"2222")
		          
		         )
		        .flatMap(sturepositroy::save)
		        .subscribe(student -> log.info("Student: {}", student));

		    };
	}


