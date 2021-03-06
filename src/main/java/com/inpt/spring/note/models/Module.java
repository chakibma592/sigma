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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Entity
@Table(	name = "modules")
public class Module implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	@Size(max = 200)
	private String modulename;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "filiereid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Filiere filiere;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModulename() {
		return modulename;
	}
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	public Filiere getFiliere() {
		return filiere;
	}
	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
	public Module(@NotBlank @Size(max = 200) String modulename, Filiere filiere) {
		super();
		this.modulename = modulename;
		this.filiere = filiere;
	}
	public Module() {
		super();
	}
	
	
}
