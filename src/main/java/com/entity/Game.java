package com.entity;

public class Game {
	private int id;
	private String title;
	private Double price;
	private int platform_id;
	private String photoName;
	private int genre_id;
	private String description;
	private String year;
	private String developer;

	public Game() {
	}

	public Game(String title, Double price, int platform_id, String photoName, int genre_id, String description,
			String developer) {
		super();
		this.title = title;
		this.price = price;
		this.platform_id = platform_id;
		this.photoName = photoName;
		this.genre_id = genre_id;
		this.description = description;
		this.developer = developer;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public Game(String title, Double price, int platform_id, String photoName, int genre_id, String description,
			String year, String developer) {
		super();
		this.title = title;
		this.price = price;
		this.platform_id = platform_id;
		this.photoName = photoName;
		this.genre_id = genre_id;
		this.description = description;
		this.year = year;
		this.developer = developer;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getPlatform_id() {
		return platform_id;
	}

	public void setPlatform_id(int platform_id) {
		this.platform_id = platform_id;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public int getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}

}
