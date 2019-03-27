package com.bbsbackend.components.lostAndFoundComponent.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.bbsbackend.types.LostAndFoundPost;

public interface LostAndFoundRepository {
	public boolean savePost(LostAndFoundPost post);
	
	public Stream<LostAndFoundPost> getAllPosts(int start,int num,boolean filter);
	
	public Optional<LostAndFoundPost> searchById(String id);
	public Stream<LostAndFoundPost> searchByName(String obj);
	public Stream<LostAndFoundPost> searchByTime(String beginTime,String endTime);
	public boolean remove(String id);
	public boolean updatePost(String id,LostAndFoundPost post);
}
