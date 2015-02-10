package com.example.Models;

public class Quiz {
	private int id;
	private String name;
	private String subject;
	private int duration;
	private int no_of_MCQ;
	private int no_of_rearrangeQ;
	private int instrutor_id;
	private String created_at;
	private String updated_at;
	private String expiry_date;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getNo_of_MCQ() {
		return no_of_MCQ;
	}
	public void setNo_of_MCQ(int no_of_MCQ) {
		this.no_of_MCQ = no_of_MCQ;
	}
	public int getNo_of_rearrangeQ() {
		return no_of_rearrangeQ;
	}
	public void setNo_of_rearrangeQ(int no_of_rearrangeQ) {
		this.no_of_rearrangeQ = no_of_rearrangeQ;
	}
	public int getInstrutor_id() {
		return instrutor_id;
	}
	public void setInstrutor_id(int instrutor_id) {
		this.instrutor_id = instrutor_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public String getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}
	

}
