package com.example.Models;

public class PublishInfo {
	private String expiry_date;
	private int group_id;
	
	public String getDate() {
		return expiry_date;
	}
	public void setDate(String date) {
		this.expiry_date = date;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
}
