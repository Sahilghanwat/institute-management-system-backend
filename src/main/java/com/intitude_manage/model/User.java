package com.intitude_manage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="User_Records")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="password")
	private String password;
	@Column(name="role")
	private String role;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(int id, String name, String password, String role) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.role=role;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public void setRole(String role)
	{
		this.role=role;
	}
	
	
	public String getRole()
	{
		return role;
	}
	
	

	
	
}
