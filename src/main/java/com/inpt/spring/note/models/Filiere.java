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
@Table(	name = "filieres")
public class Filiere implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	@Size(max = 200)
	private String filierename;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFilierename() {
		return filierename;
	}
	public void setFilierename(String filierename) {
		this.filierename = filierename;
	}
	public Filiere() {
		super();
	}
	public Filiere(@NotBlank @Size(max = 200) String filierename) {
		super();
		this.filierename = filierename;
	}
	
}
