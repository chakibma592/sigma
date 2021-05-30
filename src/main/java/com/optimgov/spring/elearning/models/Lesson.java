package com.optimgov.spring.elearning.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Lesson implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Size(max = 200)
	private String lessonname;
	@NotBlank
	@Size(max = 1000)
	private String description;
	@NotBlank
	@Size(max = 1000)
	private String videourl;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "courseid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Course course;
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "lesson")
    private Quiz quiz;
	
	public Lesson(@NotBlank @Size(max = 200) String lessonname, @NotBlank @Size(max = 1000) String description,
			@NotBlank @Size(max = 1000) String videourl, Course course) {
		super();
		this.lessonname = lessonname;
		this.description = description;
		this.videourl = videourl;
		this.course = course;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLessonname() {
		return lessonname;
	}
	public void setLessonname(String lessonname) {
		this.lessonname = lessonname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVideourl() {
		return videourl;
	}
	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}
	public Lesson() {
		super();
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
	

}
