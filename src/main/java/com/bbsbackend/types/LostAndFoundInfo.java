package com.bbsbackend.types;

public class LostAndFoundInfo {
//	post.getId(),post.getPublisher(),post.getObj()
//	post.getTime(),post.getPosition(),post.getDescription(),post.getImage(),post.getClaimant()
	private String id;
	private String publisher;
	private String obj;
	private String time;
	private String position;
	private String description;
	private String image;
	private String claimant;
	public LostAndFoundInfo(LostAndFoundPost post) {
		this(post.getId(),post.getPublisher(),post.getObj(),post.getTime(),post.getPosition(),post.getDescription(),post.getImage(),post.getClaimant());
	}
	public LostAndFoundInfo(String id, String publisher, String obj, String time, String position,String description, String image, String claimant) {
		this.id = id;
		this.publisher = publisher;
		this.obj = obj;
		this.time = time;
		this.position = position;
		this.description = description;
		this.image = image;
		this.claimant = claimant;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getObj() {
		return obj;
	}
	public void setObj(String obj) {
		this.obj = obj;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getClaimant() {
		return claimant;
	}
	public void setClaimant(String claimant) {
		this.claimant = claimant;
	}

}
