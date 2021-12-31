package com.optimgov.spring.elearning.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "semestres")
public class Semestre implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	@Size(max = 200)
	private String semestername;
	public Semestre(Long id, @NotBlank @Size(max = 200) String semestername) {
		super();
		this.id = id;
		this.semestername = semestername;
	}
	public Semestre() {
		super();
	}
	
	public Semestre(@NotBlank @Size(max = 200) String semestername) {
		super();
		this.semestername = semestername;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSemestername() {
		return semestername;
	}
	public void setSemestername(String semestername) {
		this.semestername = semestername;
	}
	
}
