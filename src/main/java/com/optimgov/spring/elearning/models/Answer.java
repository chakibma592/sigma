package com.optimgov.spring.elearning.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Answer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	@Size(max = 200)
	private String answerlib;
	@NotBlank
	private boolean correct;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "questionid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Question question;
	public Answer(@NotBlank @Size(max = 200) String answerlib, @NotBlank boolean correct, Question question) {
		super();
		this.answerlib = answerlib;
		this.correct = correct;
		this.question = question;
	}
	public Answer() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAnswerlib() {
		return answerlib;
	}
	public void setAnswerlib(String answerlib) {
		this.answerlib = answerlib;
	}
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	
}
