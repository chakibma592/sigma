package com.optimgov.spring.elearning.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Profession implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	@Size(max = 200)
	private String professionname;
		
	public Profession() {
		super();
	}
	public Profession(Long id, @NotBlank @Size(max = 200) String professionname) {
		super();
		this.id = id;
		this.professionname = professionname;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProfessionname() {
		return professionname;
	}
	public void setProfessionname(String professionname) {
		this.professionname = professionname;
	}
	
}
