package com.optimgov.spring.elearning.payload.response;

import java.util.ArrayList;

public class NoteResponse {
private String modulename;
private double moyennemodule;
private ArrayList<ElementResponse> element;
public NoteResponse(String modulename, double moyennemodule, ArrayList<ElementResponse> element) {
	super();
	this.modulename = modulename;
	this.moyennemodule = moyennemodule;
	this.element = element;
}
public NoteResponse() {
	super();
}
public String getModulename() {
	return modulename;
}
public void setModulename(String modulename) {
	this.modulename = modulename;
}
public double getMoyennemodule() {
	return moyennemodule;
}
public void setMoyennemodule(double moyennemodule) {
	this.moyennemodule = moyennemodule;
}
public ArrayList<ElementResponse> getElement() {
	return element;
}
public void setElement(ArrayList<ElementResponse> element) {
	this.element = element;
}


}
