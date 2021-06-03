package com.optimgov.spring.elearning.payload.response;

import java.util.Date;

import javax.validation.constraints.NotBlank;

public class CartResponse {
	@NotBlank
	private String coursename;
	@NotBlank
	private String courseimageurl;
	@NotBlank
	private String description;	
	private double price;	
	private double rate;
	@NotBlank
	private Date created_at;
	@NotBlank
	private boolean locked;
	
	private String topicname;
	private Long id;
	private Long courseid;
	public CartResponse(@NotBlank String coursename, @NotBlank String courseimageurl, @NotBlank String description,
			double price, double rate, @NotBlank Date created_at, @NotBlank boolean locked, String topicname, Long id) {
		super();
		this.coursename = coursename;
		this.courseimageurl = courseimageurl;
		this.description = description;
		this.price = price;
		this.rate = rate;
		this.created_at = created_at;
		this.locked = locked;
		this.topicname = topicname;
		this.id = id;
	}
	public CartResponse() {
		super();
	}
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
	public String getTopicname() {
		return topicname;
	}
	public void setTopicname(String topicname) {
		this.topicname = topicname;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCourseid() {
		return courseid;
	}
	public void setCourseid(Long courseid) {
		this.courseid = courseid;
	}
	
	public CartResponse(@NotBlank String coursename, @NotBlank String courseimageurl, @NotBlank String description,
			double price, double rate, @NotBlank Date created_at, @NotBlank boolean locked, String topicname, Long id,
			Long courseid) {
		super();
		this.coursename = coursename;
		this.courseimageurl = courseimageurl;
		this.description = description;
		this.price = price;
		this.rate = rate;
		this.created_at = created_at;
		this.locked = locked;
		this.topicname = topicname;
		this.id = id;
		this.courseid = courseid;
	}
	
	

}
