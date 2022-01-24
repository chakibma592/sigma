package com.optimgov.spring.elearning.models;

import java.io.Serializable;

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
public class ElementModule implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "teacherid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Teacher teacher;
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
	
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public ElementModule(@NotBlank @Size(max = 200) String elementname, Module module) {
		super();
		this.elementname = elementname;
		this.module = module;
	}
	public ElementModule() {
		super();
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public ElementModule( @NotBlank @Size(max = 200) String elementname, Module module, Teacher teacher) {
		super();
		
		this.elementname = elementname;
		this.module = module;
		this.teacher = teacher;
	}
	
}
