package com.bbsbackend.types;

public class UserInfo {
	private Identifier portraitIdentifier;
	private boolean gender;
	private String grade;
	private MajorValue majorValue;
	private String introduction;
	private String name;
	public UserInfo(String name,Identifier portraitIdentifier, boolean gender, String grade, MajorValue majorValue,String introduction) {
		this.portraitIdentifier = portraitIdentifier;
		this.gender = gender;
		this.grade = grade;
		this.majorValue = majorValue;
		this.introduction = introduction;
		this.name=name;
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Identifier getPortraitIdentifier() {
		return portraitIdentifier;
	}
	public void setPortraitIdentifier(Identifier portraitIdentifier) {
		this.portraitIdentifier = portraitIdentifier;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public MajorValue getMajorValue() {
		return majorValue;
	}
	public void setMajorValue(MajorValue majorValue) {
		this.majorValue = majorValue;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	
}
