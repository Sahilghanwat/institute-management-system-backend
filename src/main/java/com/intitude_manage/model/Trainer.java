package com.intitude_manage.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Trainer_Records")
public class Trainer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="Trainer_Name" ,nullable=false)
	private String name;
	@Column(name="cource")
	private String cource;
	@Column(name="Trainer_salary")
	private int salary;
	
	@OneToMany(mappedBy="trainer")
	@JsonIgnore
	private List<Student> students;
	
	public Trainer() {
		
	}

	public Trainer(Long id, String name, String cource, int salary, List<Student> students) {
		this.id = id;
		this.name = name;
		this.cource = cource;
		this.salary = salary;
		this.students = students;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCource() {
		return cource;
	}

	public void setCource(String cource) {
		this.cource = cource;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}
	
	

	
}