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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Question implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Size(max = 200)
	private String quetionlib;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "quizid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Quiz quiz;
	@OneToMany(targetEntity = Answer.class, mappedBy = "question", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Answer> listanswers= new ArrayList<>();
	public Question(@NotBlank @Size(max = 200) String quetionlib, Quiz quiz) {
		super();
		this.quetionlib = quetionlib;
		this.quiz = quiz;
	}
	public Question() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuetionlib() {
		return quetionlib;
	}
	public void setQuetionlib(String quetionlib) {
		this.quetionlib = quetionlib;
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public List<Answer> getListanswers() {
		return listanswers;
	}
	public void setListanswers(List<Answer> listanswers) {
		this.listanswers = listanswers;
	}
	
}
