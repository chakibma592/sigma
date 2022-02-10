package com.inpt.spring.note.payload.request;

public class ModuleRequest {
	private Long id;
	private String modulename;
	private long filiereid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModulename() {
		return modulename;
	}
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	public long getFiliereid() {
		return filiereid;
	}
	public void setFiliereid(long filiereid) {
		this.filiereid = filiereid;
	}
	public ModuleRequest(Long id, String modulename, long filiereid) {
		super();
		this.id = id;
		this.modulename = modulename;
		this.filiereid = filiereid;
	}
	public ModuleRequest(String modulename, long filiereid) {
		super();
		this.modulename = modulename;
		this.filiereid = filiereid;
	}
	public ModuleRequest() {
		super();
	}
	
}
