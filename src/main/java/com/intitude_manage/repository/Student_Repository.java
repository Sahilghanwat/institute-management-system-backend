package com.intitude_manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intitude_manage.model.Student;

@Repository
public interface Student_Repository extends JpaRepository<Student,Long>{

}
