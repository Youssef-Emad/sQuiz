package com.example.Models;
public class Group {
	private int id;
	private String name;
	private int instructor_id;
	private String created_at;
	private String updated_at;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getInstructor_id() {
		return instructor_id;
	}
	public void setInstructor_id(int instructor_id) {
		this.instructor_id = instructor_id;
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
	public String toString() {
		return name;
	}
}