package com.bbsbackend.components.lostAndFoundComponent.repository;

import java.util.List;

import com.bbsbackend.types.LostAndFoundPost;

public interface LostAndFoundRepository {
	public boolean publish(LostAndFoundPost post);
	
	public List<LostAndFoundPost> getAllPosts(int start,int num,boolean filter);
	
	public LostAndFoundPost searchById(String id);
	public List<LostAndFoundPost> searchByName(String obj);
	public List<LostAndFoundPost> searchByTime(String beginTime,String endTime);
	public boolean remove(String id);
	public boolean claim(String id,String claimant);
}
