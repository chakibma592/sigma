package com.optimgov.spring.elearning.payload.request;

import java.util.Date;

public class AbsenceRequest {
	private Long id;
	private int nombreheures;
	private long studentid;
	private Date dateabsence;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNombreheures() {
		return nombreheures;
	}
	public void setNombreheures(int nombreheures) {
		this.nombreheures = nombreheures;
	}
	public long getStudentid() {
		return studentid;
	}
	public void setStudentid(long studentid) {
		this.studentid = studentid;
	}
	public Date getDateabsence() {
		return dateabsence;
	}
	public void setDateabsence(Date dateabsence) {
		this.dateabsence = dateabsence;
	}
	public AbsenceRequest(Long id, int nombreheures, long studentid, Date dateabsence) {
		super();
		this.id = id;
		this.nombreheures = nombreheures;
		this.studentid = studentid;
		this.dateabsence = dateabsence;
	}
	public AbsenceRequest() {
		super();
	}
	
}
