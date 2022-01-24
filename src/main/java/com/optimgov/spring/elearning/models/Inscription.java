package com.optimgov.spring.elearning.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(	name = "inscriptions")
public class Inscription {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "studentid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Student student;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "anneeid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private AnneeUniversitaire annee;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "semestgreid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Semestre semestre;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private EObservation observation;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public AnneeUniversitaire getAnnee() {
		return annee;
	}
	public void setAnnee(AnneeUniversitaire annee) {
		this.annee = annee;
	}
	public Semestre getSemestre() {
		return semestre;
	}
	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
	public EObservation getObservation() {
		return observation;
	}
	public void setObservation(EObservation observation) {
		this.observation = observation;
	}
	public Inscription(Long id, Student student, AnneeUniversitaire annee, Semestre semestre,
			EObservation observation) {
		super();
		this.id = id;
		this.student = student;
		this.annee = annee;
		this.semestre = semestre;
		this.observation = observation;
	}
	public Inscription( Student student, AnneeUniversitaire annee, Semestre semestre,
			EObservation observation) {
		super();
		this.student = student;
		this.annee = annee;
		this.semestre = semestre;
		this.observation = observation;
	}
	public Inscription() {
		super();
		
	}
	
}
