package com.inpt.spring.note.payload.request;

import com.inpt.spring.note.models.EObservation;

public class InscriptionRequest {
	private Long id;
	private String observation;
	private long studentid ;
	private long anneeid ;
	private long semestreid ;
	public InscriptionRequest(Long id, String observation, long studentid, long anneeid, long semestreid) {
		super();
		this.id = id;
		this.observation = observation;
		this.studentid = studentid;
		this.anneeid = anneeid;
		this.semestreid = semestreid;
	}
	public InscriptionRequest() {
		super();
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public long getStudentid() {
		return studentid;
	}
	public void setStudentid(long studentid) {
		this.studentid = studentid;
	}
	public long getAnneeid() {
		return anneeid;
	}
	public void setAnneeid(long anneeid) {
		this.anneeid = anneeid;
	}
	public long getSemestreid() {
		return semestreid;
	}
	public void setSemestreid(long semestreid) {
		this.semestreid = semestreid;
	}
	
}
