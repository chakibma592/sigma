package com.optimgov.spring.elearning.payload.request;

public class NoteRequest {
	private Long id;
	private double note;
	private long studentid;
	private long semestreid;
	private long anneeid;
	private long elementid;
	
	public long getElementid() {
		return elementid;
	}
	public void setElementid(long elementid) {
		this.elementid = elementid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getNote() {
		return note;
	}
	public void setNote(double note) {
		this.note = note;
	}
	public long getStudentid() {
		return studentid;
	}
	public void setStudentid(long studentid) {
		this.studentid = studentid;
	}
	
	public long getSemestreid() {
		return semestreid;
	}
	public void setSemestreid(long semestreid) {
		this.semestreid = semestreid;
	}
	public long getAnneeid() {
		return anneeid;
	}
	public void setAnneeid(long anneeid) {
		this.anneeid = anneeid;
	}
	public NoteRequest(Long id, double note, long studentid) {
		super();
		this.id = id;
		this.note = note;
		this.studentid = studentid;
	}
	public NoteRequest() {
		super();
	}
	public NoteRequest(Long id, double note, long studentid, long semestreid, long anneeid, long elementid) {
		super();
		this.id = id;
		this.note = note;
		this.studentid = studentid;
		this.semestreid = semestreid;
		this.anneeid = anneeid;
		this.elementid = elementid;
	}
	public NoteRequest(double note, long studentid, long semestreid, long anneeid, long elementid) {
		super();
		this.note = note;
		this.studentid = studentid;
		this.semestreid = semestreid;
		this.anneeid = anneeid;
		this.elementid = elementid;
	}
	@Override
	public String toString() {
		return "NoteRequest [note=" + note + ", studentid=" + studentid + ", semestreid=" + semestreid + ", anneeid="
				+ anneeid + ", elementid=" + elementid + "]";
	}
	
}
