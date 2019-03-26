package com.bbsbackend.components.lectureComponent.repository;

import java.util.List;

import com.bbsbackend.types.LecturePost;

public interface LectureRepository {
	
	//这个获取量不知道
	public List<LecturePost> getAllLecturePost(int start,int order);
	
	public boolean removePost(String id);
	
	public boolean addPost(LecturePost post);
	
	public LecturePost searchById(String id);
	
	public List<LecturePost> searchByTime(String beginTime,String endTime);
	
	public List<LecturePost> searchByPresenter(String presenter);
	
}
