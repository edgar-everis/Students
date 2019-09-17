package com.domain.main.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Student")
public class Student {
	
	@Id
	private String id;
	private String fullname;
	private String gender;
	private String birthday;
	private String Type_doc;
	private String document;
	
	


}
