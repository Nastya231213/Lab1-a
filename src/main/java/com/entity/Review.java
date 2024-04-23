package com.entity;

public class Review {
	private int id;
	private int user_id;
	private int game_id;
	private String comment;
	private double rating;
	private String headline;
private String date;
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Review(int user_id, int game_id, String comment, double rating,String headline) {
		super();
		this.user_id = user_id;
		this.game_id = game_id;
		this.comment = comment;
		this.rating = rating;
		this.headline=headline;
	}

	public int getUser_id() {
		return user_id;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGame_id() {
		return game_id;
	}

	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
	public Review() {

	}
}
