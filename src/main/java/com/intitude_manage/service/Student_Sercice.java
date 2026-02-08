package com.intitude_manage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intitude_manage.model.Student;
import com.intitude_manage.repository.Student_Repository;

@Service
public class Student_Sercice {

	@Autowired
	private Student_Repository student_repo;
	
	//All Student SHOW 
	public List<Student> allStudnet()
	{
		return student_repo.findAll();
	}
	
	//Student By Id
	public Optional<Student> studentById(Long id) 
	{
		return student_repo.findById(id);
	}
	
	
	//Save Student
	public Student saveStudent(Student student)
	{
		return student_repo.save(student);
	}
	
	
	//Delete Stuident By Id
	public void deleteStudentById(Long id)
	{
		student_repo.deleteById(id);
	}
	
	
}
