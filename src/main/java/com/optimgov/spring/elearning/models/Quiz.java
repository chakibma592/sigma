package com.optimgov.spring.elearning.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
public class Quiz implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Size(max = 200)
	private String quiztitle;
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lessonid", nullable = false)
    private Lesson lesson;
	public Quiz(@NotBlank @Size(max = 200) String quiztitle, Lesson lesson) {
		super();
		this.quiztitle = quiztitle;
		this.lesson = lesson;
	}
	public Quiz() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuiztitle() {
		return quiztitle;
	}
	public void setQuiztitle(String quiztitle) {
		this.quiztitle = quiztitle;
	}
	public Lesson getLesson() {
		return lesson;
	}
	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
	
	
}
