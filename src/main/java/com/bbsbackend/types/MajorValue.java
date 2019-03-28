package com.bbsbackend.types;

public class MajorValue {
	private String major;
	private String school;
	private String majorCode;
	public MajorValue(String school,String major) {
		this.school=school;
		this.major=major;
	}
	public MajorValue(String major,String school,String majorCode) {
		this.major=major;
		this.school=school;
		this.majorCode=majorCode;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getMajorCode() {
		return majorCode;
	}
	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}
	
}
