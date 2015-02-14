package com.example.Models;

import com.example.Helpers.validator;

public class Quiz {
	private int id;
	private String name;
	private int no_of_MCQ;
	private int no_of_rearrangeQ;
	private String subject;
	private int duration;
	
	public String toString() {
		return name;
	}
	public void setName(String name)throws Exception {
		if(!name.matches("")){
			if(validator.isAlpha(name))       
				this.name=name.trim();		 
			else 
				throw new Exception("Quiz name must contain only letters");
		}
		else 
			throw new Exception("Please fill in missing Data");
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getnMCQ() {
		return no_of_MCQ;
	}
	
	public void setnMCQ(String nMCQ)throws Exception {
		
		if(!nMCQ.matches("")){
			if(validator.isNumber(nMCQ))       
				this.no_of_MCQ=Integer.parseInt(nMCQ);		 
			else 
				throw new Exception("You should Enter a Number in MCQ Number");
		}
		else 
			throw new Exception("Please fill in missing Data");
	
	}

	public int getnRearrangeQ() {
		return no_of_rearrangeQ;
	}
	
	public void setnRearrangeQ(String nRearrangeQ) throws Exception {
	
		if(!nRearrangeQ.matches("")){
			if(validator.isNumber(nRearrangeQ))      
				this.no_of_rearrangeQ=Integer.parseInt(nRearrangeQ);		 
			else 
				throw new Exception("You should Enter a Number in RAQ Number");
		}
		else 
			throw new Exception("Please fill in missing Data");
	

	
	}
	public String getqSubject() {
		return subject;
	}
	public void setqSubject(String qSubject) throws Exception {
		
		if(!qSubject.matches("")){
			if(validator.isAlpha(qSubject))       
				this.subject= qSubject;		 
			else 
				throw new Exception("You should Enter only letters Quiz subject");
		}
		else 
			throw new Exception("Please fill in missing Data");
	}
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(String duration)throws Exception {
		if(!duration.matches("")){
			if(validator.isNumber(duration))       
				this.duration= Integer.parseInt(duration);		 
			else 
				throw new Exception("A number must be entered in Duration field");
		}
		else 
			throw new Exception("Please fill in missing Data");
	}

	public void setQuizInfo(String nMCQ,String nRe,String QName ,String Qsubject,String duration) throws Exception {
		setName(QName);
		setqSubject(Qsubject);
		setnMCQ(nMCQ);
		setnRearrangeQ(nRe);
		setDuration(duration);
	}
	
	public int getNQuestion() {
		return no_of_MCQ + no_of_rearrangeQ;
	}
	
}
