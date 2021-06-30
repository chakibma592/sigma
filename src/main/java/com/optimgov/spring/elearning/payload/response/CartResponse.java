package com.optimgov.spring.elearning.payload.response;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.optimgov.spring.elearning.models.Level;

public class CartResponse {
	@NotBlank
	private String coursename;
	@NotBlank
	private String courseimageurl;
	@NotBlank
	private String description;	
	private String shortdescription;	
	private double price;	
	private double discount;
	@NotBlank
	private boolean locked;
	private String levelname;
	private String topicname;
	private Long id;
	private Long courseid;
	public CartResponse(@NotBlank String coursename, @NotBlank String courseimageurl, @NotBlank String description,
			double price, double discount, @NotBlank boolean locked, String topicname, Long id) {
		super();
		this.coursename = coursename;
		this.courseimageurl = courseimageurl;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.locked = locked;
		this.topicname = topicname;
		this.id = id;
	}

	public CartResponse(@NotBlank String coursename, @NotBlank String courseimageurl, @NotBlank String description,
			double price, double discount, @NotBlank boolean locked, String topicname, Long id,
			Long courseid,String levelname,String shortdescription) {
		super();
		this.coursename = coursename;
		this.courseimageurl = courseimageurl;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.locked = locked;
		this.topicname = topicname;
		this.id = id;
		this.courseid = courseid;
		this.levelname= levelname;
		this.shortdescription= shortdescription;
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
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
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
	

	public String getLevelname() {
		return levelname;
	}
	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}

	public String getShortdescription() {
		return shortdescription;
	}

	public void setShortdescription(String shortdescription) {
		this.shortdescription = shortdescription;
	}
	
	

}
