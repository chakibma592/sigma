package com.optimgov.spring.elearning.payload.response;

import java.util.Date;

public class AbsenceSingleResponse {
	private int nombreheures;
	private Date dateabsence;
	private String observation;
	private boolean justifie;
	public int getNombreheures() {
		return nombreheures;
	}
	public void setNombreheures(int nombreheures) {
		this.nombreheures = nombreheures;
	}
	public Date getDateabsence() {
		return dateabsence;
	}
	public void setDateabsence(Date dateabsence) {
		this.dateabsence = dateabsence;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public boolean isJustifie() {
		return justifie;
	}
	public void setJustifie(boolean justifie) {
		this.justifie = justifie;
	}
	public AbsenceSingleResponse(int nombreheures, Date dateabsence, String observation, boolean justifie) {
		super();
		this.nombreheures = nombreheures;
		this.dateabsence = dateabsence;
		this.observation = observation;
		this.justifie = justifie;
	}
	public AbsenceSingleResponse() {
		super();
		
	}
	public AbsenceSingleResponse(int nombreheures, Date dateabsence, String observation) {
		super();
		this.nombreheures = nombreheures;
		this.dateabsence = dateabsence;
		this.observation = observation;
		
	}

}
