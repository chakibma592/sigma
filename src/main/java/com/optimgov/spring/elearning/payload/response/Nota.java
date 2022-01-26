package com.optimgov.spring.elearning.payload.response;

public class Nota {
private String modulename;
private String elementname;
private double note;
public String getModulename() {
	return modulename;
}
public void setModulename(String modulename) {
	this.modulename = modulename;
}
public String getElementname() {
	return elementname;
}
public void setElementname(String elementname) {
	this.elementname = elementname;
}
public double getNote() {
	return note;
}
public void setNote(double note) {
	this.note = note;
}
public Nota(String modulename, String elementname, double note) {
	super();
	this.modulename = modulename;
	this.elementname = elementname;
	this.note = note;
}
public Nota() {
	super();
	
}
}
