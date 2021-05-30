package com.optimgov.spring.elearning.payload.request;

public class LessonRequest {
	private Long id;
	private String lessonname;
	private String description;
	private String videourl;
	private long courseid;
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
	public long getCourseid() {
		return courseid;
	}
	public void setCourseid(long courseid) {
		this.courseid = courseid;
	}
	
}
