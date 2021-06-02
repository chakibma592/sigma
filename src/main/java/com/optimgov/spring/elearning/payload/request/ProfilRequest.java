package com.optimgov.spring.elearning.payload.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;

public class ProfilRequest {
	@NotBlank
	private String firstname;

	@NotBlank
	private String lastname;
	@NotBlank
	private Date birthday;
	@NotBlank
	private String email;
	private Long professionid;
	private long levelid;
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getProfessionid() {
		return professionid;
	}
	public void setProfessionid(Long professionid) {
		this.professionid = professionid;
	}
	public long getLevelid() {
		return levelid;
	}
	public void setLevelid(long levelid) {
		this.levelid = levelid;
	}
	
	
}
