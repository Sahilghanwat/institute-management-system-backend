package com.intitude_manage.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;



@Document(indexName="students")
public class StudentDocument {
	
	@Id
	private String id;
	
	@Field(type = FieldType.Integer)
	private Integer studentId;
	
	@Field(type=FieldType.Text)
	private String name;
	
	@Field(type=FieldType.Keyword)
	private String city;
	@Field(type=FieldType.Keyword)
	private String course;
	@Field(type=FieldType.Keyword)
	private String email;
	
	public StudentDocument() {
		
	}
	
	public StudentDocument( Integer studentId, String name, String city, String course, String email) {
		this.id = String.valueOf(studentId);
		this.studentId = studentId;
		this.name = name;
		this.city = city;
		this.course = course;
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
