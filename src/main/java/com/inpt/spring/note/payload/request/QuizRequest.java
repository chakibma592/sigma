package com.inpt.spring.note.payload.request;

public class QuizRequest {
	private Long id;
	private String quizname;
	private long lessonid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuizname() {
		return quizname;
	}
	public void setQuizname(String quizname) {
		this.quizname = quizname;
	}
	public long getLessonid() {
		return lessonid;
	}
	public void setLessonid(long lessonid) {
		this.lessonid = lessonid;
	}
	
}
