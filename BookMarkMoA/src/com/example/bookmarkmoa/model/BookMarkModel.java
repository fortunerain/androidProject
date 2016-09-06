package com.example.bookmarkmoa.model;

import android.graphics.Bitmap;

public class BookMarkModel {
	private String url;
	private String title;
	private String visits;
	private Bitmap favicon;
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getVisits() {
		return visits;
	}
	public void setVisits(String visits) {
		this.visits = visits;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Bitmap getFavicon() {
		return favicon;
	}
	public void setFavicon(Bitmap favicon) {
		this.favicon = favicon;
	}
	
	
}
