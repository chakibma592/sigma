package com.optimgov.spring.elearning.payload.request;

public class ElementRequest {
	private Long id;
	private String elementname;
	private long moduleid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getElementname() {
		return elementname;
	}
	public void setElementname(String elementname) {
		this.elementname = elementname;
	}
	public long getModuleid() {
		return moduleid;
	}
	public void setModuleid(long moduleid) {
		this.moduleid = moduleid;
	}
	public ElementRequest(Long id, String elementname, long moduleid) {
		super();
		this.id = id;
		this.elementname = elementname;
		this.moduleid = moduleid;
	}
	public ElementRequest( String elementname, long moduleid) {
		super();
		this.elementname = elementname;
		this.moduleid = moduleid;
	}
	
}
