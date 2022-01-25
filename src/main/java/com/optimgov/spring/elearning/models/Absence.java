package com.optimgov.spring.elearning.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(	name = "absences")
public class Absence implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dateabsence;
	private int nombreheures;
	private boolean justifie;
	private String Observation;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "studentid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Student student;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "semesterid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Semestre semestre;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "anneeid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private AnneeUniversitaire annee;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDateabsence() {
		return dateabsence;
	}
	public void setDateabsence(Date dateabsence) {
		this.dateabsence = dateabsence;
	}
	public int getNombreheures() {
		return nombreheures;
	}
	public void setNombreheures(int nombreheures) {
		this.nombreheures = nombreheures;
	}
	public boolean isJustifie() {
		return justifie;
	}
	public void setJustifie(boolean justifie) {
		this.justifie = justifie;
	}
	public String getObservation() {
		return Observation;
	}
	public void setObservation(String observation) {
		Observation = observation;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Semestre getSemestre() {
		return semestre;
	}
	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
	public AnneeUniversitaire getAnnee() {
		return annee;
	}
	public void setAnnee(AnneeUniversitaire annee) {
		this.annee = annee;
	}
	public Absence() {
		super();
	}
	public Absence(Date dateabsence, int nombreheures, boolean justifie, String observation, Student student,
			Semestre semestre, AnneeUniversitaire annee) {
		super();
		this.dateabsence = dateabsence;
		this.nombreheures = nombreheures;
		this.justifie = justifie;
		Observation = observation;
		this.student = student;
		this.semestre = semestre;
		this.annee = annee;
	}
	public Absence(Long id, Date dateabsence, int nombreheures, boolean justifie, String observation, Student student,
			Semestre semestre, AnneeUniversitaire annee) {
		super();
		this.id = id;
		this.dateabsence = dateabsence;
		this.nombreheures = nombreheures;
		this.justifie = justifie;
		Observation = observation;
		this.student = student;
		this.semestre = semestre;
		this.annee = annee;
	}
	

}
