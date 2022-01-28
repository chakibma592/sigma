package com.optimgov.spring.elearning.payload.response;

import java.util.ArrayList;

public class AbsenceListResponse {
private ArrayList<AbsenceSingleResponse> listAbsence;
private int totalabsence;
public ArrayList<AbsenceSingleResponse> getListAbsence() {
	return listAbsence;
}
public void setListAbsence(ArrayList<AbsenceSingleResponse> listAbsence) {
	this.listAbsence = listAbsence;
}
public int getTotalabsence() {
	return totalabsence;
}
public void setTotalabsence(int totalabsence) {
	this.totalabsence = totalabsence;
}
public AbsenceListResponse(ArrayList<AbsenceSingleResponse> listAbsence, int totalabsence) {
	super();
	this.listAbsence = listAbsence;
	this.totalabsence = totalabsence;
}
public AbsenceListResponse() {
	super();
}

}
