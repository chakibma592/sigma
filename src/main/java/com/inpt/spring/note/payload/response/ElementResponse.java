package com.inpt.spring.note.payload.response;

public class ElementResponse {
private String elementname;
private double note;
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
public ElementResponse(String elementname, double note) {
	super();
	this.elementname = elementname;
	this.note = note;
}
public ElementResponse() {
	super();
}

}
