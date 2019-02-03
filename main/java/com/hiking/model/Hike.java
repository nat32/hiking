package com.hiking.model;

import com.sun.istack.internal.Nullable;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Hike {

	private int id;

	@NotNull @NotEmpty @Size(max=255)
	private String name;

	@NotNull @Range(min=1, max=10000)
	private double distance;

	@NotNull @Range(min=1, max=10000)
	private double duration;

	@NotNull @NotEmpty @Size(max=255)
	private String starting_point;

	@NotNull @NotEmpty @Size(max=255)
	private String ending_point;

	@NotNull @NotEmpty
	private String difficulty;

	@NotNull
	private int user_id;

	private boolean done;

	@Size(max=150)
	private String image;

	@Size(max=1000)
	private String description;


	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Nullable
	private int rating;

	public String getStarting_point() {
		return starting_point;
	}

	public void setStarting_point(String starting_point) {
		this.starting_point = starting_point;
	}

	public String getEnding_point() {
		return ending_point;
	}

	public void setEnding_point(String ending_point) {
		this.ending_point = ending_point;
	}

	public double getDuration() {
		return duration;
	}

	public String getName() {
		return name;
	}

	public double getDistance() {
		return distance;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}


	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
