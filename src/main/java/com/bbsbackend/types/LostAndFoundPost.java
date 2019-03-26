package com.bbsbackend.types;
/*
 * 将lost 和 found 都用同一个东西组织
 */
public class LostAndFoundPost {
	private String publisher;
	private String obj;
	private String time;
	private String position;
	
	//地址？
	private String description;
	private String image;
	
	
	//是什么？
	private String contact;
	private String id;
	private boolean solved;
	private String claimant;
	public LostAndFoundPost(String id,String publisher,String obj,String time,String position,String description,String image) {
		this.id=id;
		this.publisher=publisher;
		this.obj=obj;
		this.time=time;
		this.position=position;
		this.description=description;
		this.image=image;
		this.solved=false;
		this.claimant=null;
	}
	
	
	public String getClaimant() {
		return claimant;
	}


	public void setClaimant(String claimant) {
		this.claimant = claimant;
	}


	public boolean getSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
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
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
