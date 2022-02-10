package com.inpt.spring.note.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
public class Teacher extends User implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private String numtph;



public Teacher() {
	super();
}



public String getNumtph() {
	return numtph;
}



public void setNumtph(String numtph) {
	this.numtph = numtph;
}



public Teacher(String numtph) {
	super();
	this.numtph = numtph;
}




}
