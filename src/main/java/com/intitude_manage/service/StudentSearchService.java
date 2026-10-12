package com.intitude_manage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.intitude_manage.document.StudentDocument;
import com.intitude_manage.repository.StudentSearchRepository;

@Service
public class StudentSearchService {
	
	private final StudentSearchRepository repository;
	
	public StudentSearchService(StudentSearchRepository repository)
	{
		this.repository=repository;
	}
	
	public List<StudentDocument> searchByCourse(String course)
	{
		return repository.findByCourse(course);
	}
	
	 public List<StudentDocument> searchByName(String name) {
	        return repository.findByNameContaining(name);
	    
	}
	 
	  public StudentDocument save(StudentDocument student) {
	        return repository.save(student);
	    }
	  
}
