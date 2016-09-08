package model;

public class ArtworkModel {
	private String assetId;
	private String title;
	private String artworkImages;
	private String galleryLocation;
	private String prodYear;
	private String artistName;
	private String likesCnt;
	private String id;
	private String country;
	
	
	public ArtworkModel(String title, String artistName, String likesCnt) {
		super();
		this.title = title;
		this.artistName = artistName;
		this.likesCnt = likesCnt;
	}
	
	
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtworkImages() {
		return artworkImages;
	}
	public void setArtworkImages(String artworkImages) {
		this.artworkImages = artworkImages;
	}
	public String getGalleryLocation() {
		return galleryLocation;
	}
	public void setGalleryLocation(String galleryLocation) {
		this.galleryLocation = galleryLocation;
	}
	public String getProdYear() {
		return prodYear;
	}
	public void setProdYear(String prodYear) {
		this.prodYear = prodYear;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getLikesCnt() {
		return likesCnt;
	}
	public void setLikesCnt(String likesCnt) {
		this.likesCnt = likesCnt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	
}
