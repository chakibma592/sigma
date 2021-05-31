package com.optimgov.spring.elearning.payload.request;
public class AnswerRequest {
	private Long id;
	private String answerlib;
	private boolean correct;
    private long questionid;
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
	public long getQuestionid() {
		return questionid;
	}
	public void setQuestionid(long questionid) {
		this.questionid = questionid;
	}
    
}
