package com.bbsbackend.types;
/*
 * remark是什么
 */
public class LectureInfo {
	private String presenter;
	private String title;
	private String time;
	private String position;
	private String description;
	private String remark;
	
	public LectureInfo(LecturePost post) {
		this(post.getPresenter(),post.getTitle(),post.getTime(),post.getPosition(),post.getDescription(),post.getRemark());
	}

	public LectureInfo(String presenter, String title, String time, String position, String description,String remark) {
		this.presenter = presenter;
		this.title = title;
		this.time = time;
		this.position = position;
		this.description = description;
		this.remark = remark;
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
	
	public String toString() {
		return "presenter: "+presenter+" title: "+title+" time: "+time+" position: "+position+" description: "+description+" remark: "+remark;
	}
}
