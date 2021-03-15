package com.mindtree.studentApplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.studentApplication.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
