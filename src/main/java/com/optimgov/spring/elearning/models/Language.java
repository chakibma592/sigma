package com.optimgov.spring.elearning.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "langages")
public class Language {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	@Size(max = 200)
	private String langagename;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLangagename() {
		return langagename;
	}
	public void setLangagename(String langagename) {
		this.langagename = langagename;
	}
	public Language(@NotBlank @Size(max = 200) String langagename) {
		super();
		this.langagename = langagename;
	}
	public Language() {
		super();
	}
	
	
}
