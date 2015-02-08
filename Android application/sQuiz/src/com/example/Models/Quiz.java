package com.example.Models;

public class Quiz {
	private String name;
	private String subject;
	private int duration;
	private int no_of_MCQ;
	private int no_of_rearrangeQ;
	
	
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
	

}
