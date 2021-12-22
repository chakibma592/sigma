package com.optimgov.spring.elearning.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Student extends User implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column(nullable=true)
	private String numappogee ;
	public String getNumappogee() {
		return numappogee;
	}
	public void setNumappogee(String numappogee) {
		this.numappogee = numappogee;
	}
	public Student(String numappogee) {
		super();
		this.numappogee = numappogee;
	}
	public Student() {
		super();
	}
	
}
