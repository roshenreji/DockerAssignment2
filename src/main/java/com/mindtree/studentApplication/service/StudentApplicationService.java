package com.mindtree.studentApplication.service;

import com.mindtree.studentApplication.entity.Classroom;
import com.mindtree.studentApplication.entity.Student;
import com.mindtree.studentApplication.exceptions.InvalidClassroomException;
import com.mindtree.studentApplication.exceptions.InvalidStudentException;

public interface StudentApplicationService {

	public Classroom addClassroom(Classroom classroom);

	public Student addStudent(Student student,int id)throws InvalidClassroomException;

	public Student getStudentbyId(int id) throws InvalidStudentException;

	public Student updateStudentDetails(Student student, int id) throws InvalidStudentException;
}
