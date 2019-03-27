package com.bbsbackend.types;

public class Major {
	private String majorValue;
	private String majorCode;
	public Major(String majorValue,String majorCode) {
		this.majorValue=majorValue;
		this.majorCode=majorCode;
	}
	public String getMajorValue() {
		return majorValue;
	}
	public void setMajorValue(String majorValue) {
		this.majorValue = majorValue;
	}
	public String getMajorCode() {
		return majorCode;
	}
	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}
	
}
