package com.example.lab12_p01_customlistviewdemo;

public class SongModel {

	private String name;
	private String time;
	private String author;
	private int year;
	
	public SongModel(String name, String time, String author, int year) {
		super();
		this.name = name;
		this.time = time;
		this.author = author;
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}