package com.example.Models;

public class Result {
	String student;
	Integer result;
	
	public Result (String s, Integer r) {
		student = s;
		result  = r;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public int getResult() {
		if (result == null)
			return -1;
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}

}
