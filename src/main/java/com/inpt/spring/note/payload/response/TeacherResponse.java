package com.inpt.spring.note.payload.response;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.inpt.spring.note.models.Role;


public class TeacherResponse {
	private Long id;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String numtph;
	private Date birthday;
	private Set<Role> roles = new HashSet<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNumtph() {
		return numtph;
	}
	public void setNumtph(String numtph) {
		this.numtph = numtph;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	

}
