package com.example.lab16_p01_readjson;

public class Movies {

	private String title;
	private String imageUrl;
	private double rating;
    private int releaseYear;
    private String genre;
    
	public Movies(String title, String imageUrl, double rating,
			int releaseYear, String genre) {
		this.title = title;
		this.imageUrl = imageUrl;
		this.rating = rating;
		this.releaseYear = releaseYear;
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
}
