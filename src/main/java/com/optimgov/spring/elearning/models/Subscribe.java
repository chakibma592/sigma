package com.optimgov.spring.elearning.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Value;

@Entity
public class Subscribe implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Value("${some.key:false}")
	private boolean payed;
	@Column(name="subscribing_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date subscribingDate;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "userid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "courseid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Course course;
	public Subscribe(Long id, boolean payed, Date subscribingDate) {
		super();
		this.id = id;
		this.payed = payed;
		this.subscribingDate = subscribingDate;
	}
	public Subscribe() {
		super();
	}
	
	public Subscribe(boolean payed, Date subscribingDate, Course course, User user) {
		super();
		this.payed = payed;
		this.subscribingDate = subscribingDate;
		this.user = user;
		this.course = course;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isPayed() {
		return payed;
	}
	public void setPayed(boolean payed) {
		this.payed = payed;
	}
	public Date getSubscribingDate() {
		return subscribingDate;
	}
	public void setSubscribingDate(Date subscribingDate) {
		this.subscribingDate = subscribingDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	

}
