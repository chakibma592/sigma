package com.optimgov.spring.elearning.models;

import java.io.Serializable;
import java.util.Date;
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
import org.springframework.beans.factory.annotation.Value;
@Entity
public class Course extends AuditModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	@Size(max = 1000)
	private String shortdescription;	
	private double price;
	@Value("${some.key:0}")
	private double discount;
	@Value("${some.key:false}")
	private boolean locked;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "topicid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Topic topic;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "teacherid", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Teacher teacher;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "levelid", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Level level;
	
	public Course(@NotBlank @Size(max = 200) String coursename, @NotBlank @Size(max = 1000) String description,
			@NotBlank double price, @NotBlank double rate, Date created_at, boolean locked, Topic topic, Teacher teacher) {
		super();
		this.coursename = coursename;
		this.description = description;
		this.price = price;
		this.discount = rate;
		this.locked = locked;
		this.topic = topic;
		this.teacher=teacher;
	}
	
	public Course(@NotBlank @Size(max = 200) String coursename, @Size(max = 1000) String courseimageurl,
			@NotBlank @Size(max = 1000) String description, @NotBlank double price, @NotBlank double rate,
			Date created_at, boolean locked, Topic topic, Teacher teacher) {
		super();
		this.coursename = coursename;
		this.courseimageurl = courseimageurl;
		this.description = description;
		this.price = price;
		this.discount = rate;
		this.locked = locked;
		this.topic = topic;
		this.teacher=teacher;
	}
	public Course(@NotBlank @Size(max = 200) String coursename, @Size(max = 1000) String courseimageurl,
			@NotBlank @Size(max = 1000) String description, @NotBlank double price, @NotBlank double rate, boolean locked, Topic topic, Teacher teacher) {
		super();
		this.coursename = coursename;
		this.courseimageurl = courseimageurl;
		this.description = description;
		this.price = price;
		this.discount = rate;
		this.locked = locked;
		this.topic = topic;
		this.teacher=teacher;
	}

	public Course(@NotBlank @Size(max = 200) String coursename, @Size(max = 1000) String courseimageurl,
			@NotBlank @Size(max = 1000) String description, @Size(max = 1000) String shortdescription, double price,
			double discount, boolean locked, Topic topic, Teacher teacher, Level level) {
		super();
		this.coursename = coursename;
		this.courseimageurl = courseimageurl;
		this.description = description;
		this.shortdescription = shortdescription;
		this.price = price;
		this.discount = discount;
		this.locked = locked;
		this.topic = topic;
		this.teacher = teacher;
		this.level = level;
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
	public double getDiscount() {
		return this.discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
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

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String getShortdescription() {
		return shortdescription;
	}

	public void setShortdescription(String shortdescription) {
		this.shortdescription = shortdescription;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}


}
