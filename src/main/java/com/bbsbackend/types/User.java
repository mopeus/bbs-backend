package com.bbsbackend.types;
//(String username,Identifier portraitIdentifier,boolean gender,
//		String grade,String major,String introduction)
//id
public class User {
	private String id;
	private String name;
	private Identifier portraitIdentifier;
	private boolean gender;
	private String grade;
	private MajorValue majorValue;
	private String introduction;
	public User(String name,Identifier portraitIdentifier, boolean gender, String grade, MajorValue majorValue,String introduction) {
		this.name=name;
		this.portraitIdentifier = portraitIdentifier;
		this.gender = gender;
		this.grade = grade;
		this.majorValue = majorValue;
		this.introduction = introduction;
	}
	public User(String id,String name, Identifier portraitIdentifier, boolean gender, String grade, MajorValue majorValue,String introduction) {
		this.id = id;
		this.name=name;
		this.portraitIdentifier = portraitIdentifier;
		this.gender = gender;
		this.grade = grade;
		this.majorValue = majorValue;
		this.introduction = introduction;
	}
	public void update(User newuser) {
		this.portraitIdentifier = newuser.getPortraitIdentifier();
		this.gender = newuser.getGender();
		this.grade = newuser.getGrade();
		this.majorValue = newuser.getMajorValue();
		this.introduction = newuser.getIntroduction();
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Identifier getPortraitIdentifier() {
		return portraitIdentifier;
	}
	public void setPortraitIdentifier(Identifier portraitIdentifier) {
		this.portraitIdentifier = portraitIdentifier;
	}
	public boolean getGender() {
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
