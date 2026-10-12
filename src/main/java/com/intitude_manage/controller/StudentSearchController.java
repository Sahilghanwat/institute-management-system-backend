package com.intitude_manage.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intitude_manage.document.StudentDocument;
import com.intitude_manage.service.StudentSearchService;

@RestController
@RequestMapping("/api/student/search")
public class StudentSearchController {
	
	private final StudentSearchService studentSearchSer;
	
	public StudentSearchController(StudentSearchService studentSearchSer) 
	{
		this.studentSearchSer=studentSearchSer;
	}
	
	
	@GetMapping("/name")
	public List<StudentDocument> searchByName(@RequestParam String name)
	{
		return studentSearchSer.searchByName(name);
	}
	
	
	// Search students by course
    @GetMapping("/course")
    public List<StudentDocument> searchByCourse(
            @RequestParam String course) {

        return studentSearchSer.searchByCourse(course);
    }
    
    

}
