package com.bbsbackend.types;

public class LecturePost {
	private String presenter;
	private String title;
	//什么是description and remark?
	private String time;
	private String position;
	private String description;
	private String remark;
	private String id;
	public LecturePost(String presenter,String title,String time,String position,String description,String remark,String id) {
		this.presenter=presenter;
		this.title=title;
		this.time=time;
		this.position=position;
		this.description=description;
		this.remark=remark;
		this.id=id;
	}
	public String getPresenter() {
		return presenter;
	}
	public void setPresenter(String presenter) {
		this.presenter = presenter;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String toString() {
		return " "+this.presenter+" "+this.title+" "+this.time+" "+this.position+" "+this.description+" "+this.remark+" "+this.id;
	}
	
}
