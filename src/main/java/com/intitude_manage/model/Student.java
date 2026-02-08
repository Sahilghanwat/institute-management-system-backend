package com.intitude_manage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Student_Records")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="name", nullable=false)
	private String name;
	@Column(name="address")
	private String address;
	@Column(name="age")
	private int age;
	@Column(name="email" , unique=true)
	private String email;
	
	
	@ManyToOne
	@JoinColumn(name="Trainer_id")
	private Trainer trainer;


	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Student(Long id, String name, String address, int age, String email, Trainer trainer) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.age = age;
		this.email = email;
		this.trainer = trainer;
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


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Trainer getTrainer() {
		return trainer;
	}


	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}
	
	

}
