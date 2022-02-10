package com.inpt.spring.note.models;

import java.io.Serializable;

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

@Entity
@Table(	name = "notes")
public class Note implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private double note;
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
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "elementid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ElementModule element;
	private String observation;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getNote() {
		return note;
	}
	public void setNote(double note) {
		this.note = note;
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
	public ElementModule getElement() {
		return element;
	}
	public void setElement(ElementModule element) {
		this.element = element;
	}
	public Note() {
		super();
	}
	public Note(double note, Student student, Semestre semestre, AnneeUniversitaire annee, ElementModule element) {
		super();
		this.note = note;
		this.student = student;
		this.semestre = semestre;
		this.annee = annee;
		this.element = element;
	}
	public Note(Long id, double note, Student student, Semestre semestre, AnneeUniversitaire annee,
			ElementModule element) {
		super();
		this.id = id;
		this.note = note;
		this.student = student;
		this.semestre = semestre;
		this.annee = annee;
		this.element = element;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public Note(double note, Student student, Semestre semestre, AnneeUniversitaire annee, ElementModule element,
			String observation) {
		super();
		this.note = note;
		this.student = student;
		this.semestre = semestre;
		this.annee = annee;
		this.element = element;
		this.observation = observation;
	}
	
	

}
