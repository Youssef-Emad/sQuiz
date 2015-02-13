package com.example.Models;

public class Question {
	private String text;
	private int mark;
	private String[] choices;
	private String right_answer;
	
	public Question(String text, String[] choices, String right_answer) {
		this.text = text;
		this.choices = choices;
		this.right_answer = right_answer;
		mark = 1;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public String[] getChoices() {
		return choices;
	}
	public void setChoices(String[] choices) {
		this.choices = choices;
	}
	public String getRight_answer() {
		return right_answer;
	}
	public void setRight_answer(String right_answer) {
		this.right_answer = right_answer;
	}
}
