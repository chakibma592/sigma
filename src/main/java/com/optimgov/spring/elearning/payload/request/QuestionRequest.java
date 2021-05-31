package com.optimgov.spring.elearning.payload.request;

public class QuestionRequest {
	private Long id;
	private String questionlib;
	private long quizid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestionlib() {
		return questionlib;
	}
	public void setQuestionlib(String questionlib) {
		this.questionlib = questionlib;
	}
	public long getQuizid() {
		return quizid;
	}
	public void setQuizid(long quizid) {
		this.quizid = quizid;
	}
	
}
