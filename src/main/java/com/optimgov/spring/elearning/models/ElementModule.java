package com.optimgov.spring.elearning.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(	name = "elements")
public class ElementModule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	@Size(max = 200)
	private String elementname;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "moduleid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Module module;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getElementname() {
		return elementname;
	}
	public void setElementname(String elementname) {
		this.elementname = elementname;
	}
	
}
