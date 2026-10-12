package com.intitude_manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.intitude_manage.model.Student;
import com.intitude_manage.service.Student_Sercice;
import com.intitude_manage.service.Trainer_Service;

@Controller
public class Student_Controller {
	
	@Autowired
	private Student_Sercice student_ser;
	@Autowired
	private Trainer_Service trainer_ser;
	
	
	
	@GetMapping("/getAllStudent")
	public String ShowAllStudent(Model model)
	{
		model.addAttribute("listStudent", student_ser.allStudnet());
		return "View_All_Student";
	}
	
	
	@GetMapping("/add_Student_Form")
	public String studentForm(Model model)
	{
		Student stu = new Student();
		model.addAttribute("student", stu);
		model.addAttribute("listTrainer", trainer_ser.getAllTariner());
		return "New_Student_From";
	}
	
	
	@PostMapping("/addStudent")
	public String addStudent(@ModelAttribute("student") Student student)
	{
		student_ser.saveStudent(student);
		return "redirect:/getAllStudent";
	}
	
	
	@GetMapping("/updateStudent/{id}")
	public String UpdateStudentById(@PathVariable Long id , Model model)
	{
		Student student = student_ser.studentById(id).orElseThrow();
		model.addAttribute("student2", student);
		model.addAttribute("listTrainer", trainer_ser.getAllTariner());
		
		return "update_Student_Form";
	}
	
	
	@PostMapping("/student/{id}")
	public String UpadateStudent(@PathVariable Long id , @ModelAttribute("student2") Student student)
	{
		student.setId(id);
		student_ser.saveStudent(student);
		return "redirect:/getAllStudent";
	}
	
	
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudentById(@PathVariable Long id)
	{
		student_ser.deleteStudentById(id);
		return "redirect:/getAllStudent";
	}
	

}
