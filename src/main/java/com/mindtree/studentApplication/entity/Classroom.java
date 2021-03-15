package com.mindtree.studentApplication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Classroom {

	@Id
	@Column(name = "id")
	private int id;
	@NotNull
	@Size(max=1, message="Name should be maximum of 1 character long")
	private String name;
	@NotNull
	private int numberOfStudents;
	public Classroom() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Classroom(int id, String name, int numberOfStudents) {
		super();
		this.id = id;
		this.name = name;
		this.numberOfStudents = numberOfStudents;
	}


	public Classroom(String name, int numberOfStudents) {
		super();
		this.name = name;
		this.numberOfStudents = numberOfStudents;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberOfStudents() {
		return numberOfStudents;
	}
	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}
	
	
	
	
}
