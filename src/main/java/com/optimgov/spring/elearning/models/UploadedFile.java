package com.optimgov.spring.elearning.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
public class UploadedFile implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	@Size(max = 200)
	private String fileurl;
	public UploadedFile(@NotBlank @Size(max = 200) String fileurl) {
		super();
		this.fileurl = fileurl;
	}
	public UploadedFile() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFileurl() {
		return fileurl;
	}
	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}
	public UploadedFile(Long id, @NotBlank @Size(max = 200) String fileurl) {
		super();
		this.id = id;
		this.fileurl = fileurl;
	}
	
	
}
