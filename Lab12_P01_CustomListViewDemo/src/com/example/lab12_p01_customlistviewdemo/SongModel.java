package com.example.lab12_p01_customlistviewdemo;

public class SongModel {

	private String name;
	private String time;
	private String author;
	private int year;
	private boolean selected;
	
	public SongModel(String name, String time, String author, int year) {
		this.name = name;
		this.time = time;
		this.author = author;
		this.year = year;
	}
	
	@Override
	public String toString() {
		return name + " - " + time + " - " + author + " - " + year;
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

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
