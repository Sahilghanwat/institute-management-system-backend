
package com.intitude_manage.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intitude_manage.model.Student;
import com.intitude_manage.service.Student_Sercice;

@RestController
@RequestMapping("/api/v1/students")
public class StudentRestController {

    private final Student_Sercice studentService;

    public StudentRestController(Student_Sercice studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.allStudnet();
        return ResponseEntity.ok(students);
    }
    
	
	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
	
	    Student savedStudent = studentService.saveStudent(student);
	
	    return ResponseEntity
	            .status(HttpStatus.CREATED)
	            .body(savedStudent);
	}


@GetMapping("/{id}")
public ResponseEntity<Student> getStudentById(@PathVariable Long id) {

    return studentService.studentById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
}




@PutMapping("/{id}")
public ResponseEntity<Student> updateStudent(
        @PathVariable Long id,
        @RequestBody Student student) {

    return studentService.studentById(id)
            .map(existingStudent -> {
                existingStudent.setName(student.getName());
                existingStudent.setAddress(student.getAddress());
                existingStudent.setAge(student.getAge());
                existingStudent.setEmail(student.getEmail());

                Student updatedStudent =
                        studentService.saveStudent(existingStudent);

                return ResponseEntity.ok(updatedStudent);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
}




@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {

    return studentService.studentById(id)
            .map(student -> {
                studentService.deleteStudentById(id);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
}


    
    
    
    
    
}
