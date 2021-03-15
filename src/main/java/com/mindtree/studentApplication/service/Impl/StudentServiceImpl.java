package com.mindtree.studentApplication.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.studentApplication.dao.ClassroomRepository;
import com.mindtree.studentApplication.dao.StudentRepository;
import com.mindtree.studentApplication.entity.Classroom;
import com.mindtree.studentApplication.entity.Student;
import com.mindtree.studentApplication.exceptions.InvalidClassroomException;
import com.mindtree.studentApplication.exceptions.InvalidStudentException;
import com.mindtree.studentApplication.service.StudentApplicationService;


@Service
public class StudentServiceImpl implements StudentApplicationService {

	@Autowired
	private StudentRepository studentrepo;
	@Autowired
	private ClassroomRepository classrepo;
	
	@Override
	public Classroom addClassroom(Classroom classroom) {
		return classrepo.save(classroom);
	}
	@Override
	public Student addStudent(Student student, int id) throws InvalidClassroomException {
		Classroom classses=classrepo.findById(id).orElseThrow(() -> new InvalidClassroomException("Invalid Classroom Id"));
		student.setClassroom(classses); 
		return studentrepo.save(student);
	}
	@Override
	public Student getStudentbyId(int id) throws InvalidStudentException {
		/*List<Student> student=new ArrayList<Student>();
		studentrepo.findAll().forEach(n -> student.add(n));
		for(Student stud: student) {
			if(stud.getId()==id) {
				return stud;
			}
		}
		return null;*/
		return studentrepo.findById(id).orElseThrow(() -> new InvalidStudentException("Invalid id"));
	}
	@Override
	public Student updateStudentDetails(Student newStudent, int id) throws InvalidStudentException {
		return studentrepo.findById(id).map(student ->{
			student.setAge(newStudent.getAge());
			return studentrepo.save(student);
			
			}).orElseThrow(() ->  new InvalidStudentException("Invalid Student Id"));
	}

	/*
	 * @Override public Student getStudentbyId(int id) throws
	 * InvalidStudentException {
	 * 
	 * return studentrepo.findById(id).orElseThrow(() -> new
	 * InvalidStudentException("Invalid id")); }
	 * 
	 * @Override public Student addStudent(Student student,int id) throws
	 * InvalidClassroomException { Classroom
	 * classses=classrepo.findById(id).orElseThrow(() -> new
	 * InvalidClassroomException()); student.setClassroom(classses); return
	 * studentrepo.save(student); }
	 * 
	 * @Override public Student updateStudentDetails(Student newStudent, int id)
	 * throws InvalidStudentException { return studentrepo.findById(id).map(student
	 * ->{ student.setAge(newStudent.getAge()); return studentrepo.save(student);
	 * 
	 * }).orElseThrow(() -> new InvalidStudentException("Invalid Id"));
	 * 
	 * }
	 * 
	 * @Override public Classroom addClassroom(Classroom classroom) { return
	 * classrepo.save(classroom); }
	 */

}
