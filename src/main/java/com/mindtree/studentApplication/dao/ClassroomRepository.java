package com.mindtree.studentApplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.studentApplication.entity.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Integer>{

}
