package com.inpt.spring.note.payload.request;

import java.util.Date;

public class AbsenceRequest {
	private Long id;
	private int nombreheures;
	private long studentid;
	private long semestreid;
	private long anneeid;
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
	public long getSemestreid() {
		return semestreid;
	}
	public void setSemestreid(long semestreid) {
		this.semestreid = semestreid;
	}
	public long getAnneeid() {
		return anneeid;
	}
	public void setAnneeid(long anneeid) {
		this.anneeid = anneeid;
	}
	public AbsenceRequest(Long id, int nombreheures, long studentid, long semestreid, long anneeid, Date dateabsence) {
		super();
		this.id = id;
		this.nombreheures = nombreheures;
		this.studentid = studentid;
		this.semestreid = semestreid;
		this.anneeid = anneeid;
		this.dateabsence = dateabsence;
	}
	
}
