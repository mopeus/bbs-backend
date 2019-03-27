package com.bbsbackend.components.lectureComponent.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.bbsbackend.types.LecturePost;

public interface LectureRepository {
	
	//这个获取量不知道
	public Stream<LecturePost> getAllLecturePost(int start,int order);
	
	public boolean removePost(String id);
	
	public boolean addPost(LecturePost post);
	
	public Optional<LecturePost> searchById(String id);
	
	public Stream<LecturePost> searchByTime(String beginTime,String endTime);
	
	public Stream<LecturePost> searchByPresenter(String presenter);
	
}
