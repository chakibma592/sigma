package com.optimgov.spring.elearning.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
public class Teacher extends User implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Column(nullable=true)
private String biographie;

public Teacher(String biographie) {
	super();
	this.biographie = biographie;
}


public Teacher() {
	super();
}


public String getBiographie() {
	return biographie;
}

public void setBiographie(String biographie) {
	this.biographie = biographie;
}


}
