package com.optimgov.spring.elearning.payload.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;

public class SubscribeRequest {
	@NotBlank
	private Date subscribingDate;

	@NotBlank
	private Long userid;
	
	@NotBlank
	private Long courseid;

	public Date getSubscribingDate() {
		return subscribingDate;
	}

	public void setSubscribingDate(Date subscribingDate) {
		this.subscribingDate = subscribingDate;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getCourseid() {
		return courseid;
	}

	public void setCourseid(Long courseid) {
		this.courseid = courseid;
	}
	
}
