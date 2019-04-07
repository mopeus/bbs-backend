package com.bbsbackend.components.like.common;

import java.util.Objects;

public class LikeId {
	public final String value;
	private LikeId(String value) {
		this.value=value;
	}
	public LikeId modValue(String value) {
		return new LikeId(value);
	}
	public static LikeId of(String value) {
		return new LikeId(value);
	}
	
	public String toString() {
		return "LikeId"+"("+"'"+value+"'"+")";
	}
	
	public boolean equals(Object o) {
		if(this==o)return true;
		if(o==null||getClass()!=o.getClass())return false;
		LikeId other=(LikeId)o;
		return Objects.equals(this.value, other.value);
	}
	
	public int hashCode() {
		return Objects.hash(value);
	}
}
