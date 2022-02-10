package com.inpt.spring.note.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "annees")
public class AnneeUniversitaire implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	@Size(max = 200)
	private String anneename;
	public AnneeUniversitaire(Long id, @NotBlank @Size(max = 200) String anneename) {
		super();
		this.id = id;
		this.anneename = anneename;
	}
	public AnneeUniversitaire(@NotBlank @Size(max = 200) String anneename) {
		super();
		this.anneename = anneename;
	}
	public AnneeUniversitaire() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAnneename() {
		return anneename;
	}
	public void setAnneename(String anneename) {
		this.anneename = anneename;
	}
	

}
