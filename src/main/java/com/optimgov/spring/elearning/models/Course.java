package com.optimgov.spring.elearning.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import org.springframework.beans.factory.annotation.Value;
@Entity
public class Course implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	@Size(max = 200)
	private String coursename;
	@Size(max = 1000)
	private String courseimageurl;
	@NotBlank
	@Size(max = 1000)
	private String description;
	@NotBlank
	private double price;
	@NotBlank
	@Value("${some.key:0}")
	private double rate;
	@Column(name="created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date created_at;
	@Value("${some.key:false}")
	private boolean locked;
	 @ManyToOne(fetch = FetchType.EAGER, optional = false)
		@JoinColumn(name = "topicid", nullable = false)
		@OnDelete(action = OnDeleteAction.CASCADE)
	    private Topic topic;
	
	public Course(@NotBlank @Size(max = 200) String coursename, @NotBlank @Size(max = 1000) String description,
			@NotBlank double price, @NotBlank double rate, Date created_at, boolean locked, Topic topic) {
		super();
		this.coursename = coursename;
		this.description = description;
		this.price = price;
		this.rate = rate;
		this.created_at = created_at;
		this.locked = locked;
		this.topic = topic;
	}
	
	public Course(@NotBlank @Size(max = 200) String coursename, @Size(max = 1000) String courseimageurl,
			@NotBlank @Size(max = 1000) String description, @NotBlank double price, @NotBlank double rate,
			Date created_at, boolean locked, Topic topic) {
		super();
		this.coursename = coursename;
		this.courseimageurl = courseimageurl;
		this.description = description;
		this.price = price;
		this.rate = rate;
		this.created_at = created_at;
		this.locked = locked;
		this.topic = topic;
	}
	public Course(@NotBlank @Size(max = 200) String coursename, @Size(max = 1000) String courseimageurl,
			@NotBlank @Size(max = 1000) String description, @NotBlank double price, @NotBlank double rate, boolean locked, Topic topic) {
		super();
		this.coursename = coursename;
		this.courseimageurl = courseimageurl;
		this.description = description;
		this.price = price;
		this.rate = rate;
		this.locked = locked;
		this.topic = topic;
	}

	public Course() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
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
	public void setLooked(boolean locked) {
		this.locked = locked;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	public String getCourseimageurl() {
		return courseimageurl;
	}
	public void setCourseimageurl(String courseimageurl) {
		this.courseimageurl = courseimageurl;
	}


}
