package com.optimgov.spring.elearning.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
public class Level {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	@Size(max = 200)
	private String levelname;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLevelname() {
		return levelname;
	}
	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}
	public Level(Long id, @NotBlank @Size(max = 200) String levelname) {
		super();
		this.id = id;
		this.levelname = levelname;
	}
	public Level(@NotBlank @Size(max = 200) String levelname) {
		super();
		this.levelname = levelname;
	}
	public Level() {
		super();
	}
	
}
