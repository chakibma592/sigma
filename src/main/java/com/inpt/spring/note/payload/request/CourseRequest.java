package com.inpt.spring.note.payload.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;


public class CourseRequest {
	@NotBlank
	private String coursename;
	@NotBlank
	private String courseimageurl;
	@NotBlank
	private String description;	
	private double price;	
	private double rate;
	
	private Date created_at;
	
	private boolean locked;	
	private Long topicid;
	private Long teacherid;
	
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getCourseimageurl() {
		return courseimageurl;
	}
	public void setCourseimageurl(String courseimageurl) {
		this.courseimageurl = courseimageurl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public Long getTopicid() {
		return topicid;
	}
	public void setTopicid(Long topicid) {
		this.topicid = topicid;
	}
	public Long getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(Long teacherid) {
		this.teacherid = teacherid;
	}
	
	
}
