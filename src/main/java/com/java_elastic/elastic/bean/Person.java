package com.java_elastic.elastic.bean;

import java.util.Date;

public class Person {
	private String fullName;
	private int id;
	private Date dateOfBirth;

	

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(String fullName, int id, Date dateOfBirth) {
		super();
		this.fullName = fullName;
		this.id = id;
		this.dateOfBirth = dateOfBirth;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
	

}
