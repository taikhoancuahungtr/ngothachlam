package com.example.lab08_p01_fragmentdemo;

public class Student {

	private int ID;
	private String name;
	private int score;
	private String school;
	
	public Student(int iD, String name, int score, String school) {
		ID = iD;
		this.name = name;
		this.score = score;
		this.school = school;
	}

	@Override
	public String toString() {
		return ID + " - " + name;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
}
