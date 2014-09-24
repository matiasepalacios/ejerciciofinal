package com.matiaspalacios.trabajofinal.classes;

import android.R.integer;

public class Movies {
	
	/**
	 * constants
	 */
	public static final String _ID = "id";
	public static final String TITLE = "title";
	public static final String YEAR = "year";
	public static final String RUNTIME = "runtime";
	public static final String RELEASE_DATE = "relase_date";
	public static final String CRITICS_SCORE = "critics_score";
	public static final String AUDIENCE_SCORE = "audience_score";
	public static final String POSTERS_THUMBNAIL = "posters_thumbnail";
	public static final String POSTERS_PROFILE = "posters_profile";
	public static final String POSTERS_DETAILED = "posters_detailed";
	public static final String POSTERS_ORIGINAL = "posters_original";
	public static final String COMMENTS = "comments";
	
	
	/**
	 * variables
	 */
	private long id;
	private String title;
	private int year;
	private int runtime;
	private String relase_date;
	private String critics_score;
	private String audience_score;
	private String posters_thumbnail;
	private String posters_profile;
	private String posters_detailed;
	private String posters_original;
	private String comments;
	
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getRuntime() {
		return runtime;
	}
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	public String getRelase_date() {
		return relase_date;
	}
	public void setRelase_date(String relase_date) {
		this.relase_date = relase_date;
	}
	public String getCritics_score() {
		return critics_score;
	}
	public void setCritics_score(String critics_score) {
		this.critics_score = critics_score;
	}
	public String getAudience_score() {
		return audience_score;
	}
	public void setAudience_score(String audience_score) {
		this.audience_score = audience_score;
	}
	public String getPosters_thumbnail() {
		return posters_thumbnail;
	}
	public void setPosters_thumbnail(String posters_thumbnail) {
		this.posters_thumbnail = posters_thumbnail;
	}
	public String getPosters_profile() {
		return posters_profile;
	}
	public void setPosters_profile(String posters_profile) {
		this.posters_profile = posters_profile;
	}
	public String getPosters_detailed() {
		return posters_detailed;
	}
	public void setPosters_detailed(String posters_detailed) {
		this.posters_detailed = posters_detailed;
	}
	public String getPosters_original() {
		return posters_original;
	}
	public void setPosters_original(String posters_original) {
		this.posters_original = posters_original;
	}
	
	
	
	

}
