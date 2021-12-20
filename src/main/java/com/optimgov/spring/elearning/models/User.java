package com.optimgov.spring.elearning.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;
	
	@Size(max = 20)
	private String firstname;
	
	@Size(max = 20)
	private String lastname;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	@Value("${some.key:false}")
	private boolean activate;
	@Column(name="created_at")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date created_at;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	/*@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "professionid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Profession profession;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "studieslevelid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
    private StudiesLevel studieslevel;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "fileid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UploadedFile file;*/
	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.activate=false;
		
	}
	public User(@NotBlank @Size(max = 20) String username, @Size(max = 20) String firstname,
			@Size(max = 20) String lastname, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 120) String password, Date birthday, boolean activate, Date created_at,
			Set<Role> roles, Profession profession, StudiesLevel studieslevel, UploadedFile file) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
		this.activate = activate;
		this.created_at = created_at;
		this.roles = roles;
		//this.profession = profession;
		//this.studieslevel = studieslevel;
		//this.file = file;
	}

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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean isActivate() {
		return activate;
	}

	public void setActivate(boolean activate) {
		this.activate = activate;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	

	/*public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}



	public StudiesLevel getStudieslevel() {
		return studieslevel;
	}

	public void setStudieslevel(StudiesLevel studieslevel) {
		this.studieslevel = studieslevel;
	}*/

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

	
	/*public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}*/



	



	
	
}
