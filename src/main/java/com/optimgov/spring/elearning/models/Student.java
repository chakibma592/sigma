package com.optimgov.spring.elearning.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Student extends User implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column(nullable=true)
	private String numappogee ;
	/*@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "semestreid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Semestre semestre;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "anneeid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private AnneeUniversitaire annee;*/
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "filiereid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Filiere filiere;
	public String getNumappogee() {
		return numappogee;
	}
	public void setNumappogee(String numappogee) {
		this.numappogee = numappogee;
	}
	public Student(String numappogee) {
		super();
		this.numappogee = numappogee;
	}
	public Student() {
		super();
	}
	public Filiere getFiliere() {
		return filiere;
	}
	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
	public Student(String numappogee, Filiere filiere) {
		super();
		this.numappogee = numappogee;
		this.filiere = filiere;
	}
	
	
}
