package com.inpt.spring.note.payload.request;

import java.util.Date;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StudentRequest {
	private String username;
	private Long id;
	private String firstname;	
	private String lastname;
	@Email
	private String email;
	private String numappogee ;
	private String numtph ;
	private long filiereid ;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public StudentRequest(String firstname, String lastname, @Email String email, String numappogee,
			long filiereid, Date birthday) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.numappogee = numappogee;
		this.filiereid = filiereid;
		this.birthday = birthday;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumappogee() {
		return numappogee;
	}
	public void setNumappogee(String numappogee) {
		this.numappogee = numappogee;
	}
	public long getFiliereid() {
		return filiereid;
	}
	public void setFiliereid(long filiereid) {
		this.filiereid = filiereid;
	}
	public StudentRequest(String username, String firstname, String lastname, @Email String email, String numappogee,
			long filiereid) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.numappogee = numappogee;
		this.filiereid = filiereid;
	}
	public StudentRequest() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumtph() {
		return numtph;
	}
	public void setNumtph(String numtph) {
		this.numtph = numtph;
	}
	
}
