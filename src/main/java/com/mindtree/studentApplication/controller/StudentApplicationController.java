package com.mindtree.studentApplication.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.studentApplication.entity.Classroom;
import com.mindtree.studentApplication.entity.Student;
import com.mindtree.studentApplication.exceptions.InvalidStudentException;
import com.mindtree.studentApplication.service.Impl.StudentServiceImpl;

@RestController
@RequestMapping("/student")
public class StudentApplicationController {
	
	@Autowired
	private StudentServiceImpl service;

	@PostMapping("/addClassroom")
	public ResponseEntity<?> addClassroom(@Valid   @RequestBody Classroom classroom){
		
		Map<String,Object>map= new LinkedHashMap<>();
		try {
			Classroom classes=service.addClassroom(classroom);
			map.put("Classroom Added", classes);
			map.put("Message", "Sucessfully added");
			return new ResponseEntity<>(map, HttpStatus.CREATED);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}
	}
	
	

	@PostMapping("/addStudent/{id}")
	public ResponseEntity<?> addStudent(@Valid  @RequestBody Student student,@PathVariable int id){
		
		Map<String,Object>map= new LinkedHashMap<>();
		try {
			Student stu=service.addStudent(student,id);
			map.put("Student Added", stu);
			map.put("Message", "Sucessfully added");
			return new ResponseEntity<>(map, HttpStatus.CREATED);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/getStudent/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable int  id){
		Map<String,Object>map= new LinkedHashMap<>();
		try {
			Student stu=service.getStudentbyId(id);
			map.put("Student Details", stu);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (InvalidStudentException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PutMapping("/updateAge/{id}")
	public ResponseEntity<?> updateStudentAgeById(@RequestBody Student student ,@PathVariable int  id){
		try {
			service.updateStudentDetails(student, id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (InvalidStudentException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
}
