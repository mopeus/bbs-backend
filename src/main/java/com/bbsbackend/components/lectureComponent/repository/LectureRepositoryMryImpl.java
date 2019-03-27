package com.bbsbackend.components.lectureComponent.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.security.auth.x500.X500Principal;

import com.bbsbackend.types.LecturePost;

public class LectureRepositoryMryImpl implements LectureRepository{
	public  List<LecturePost>lecturePosts;
	
	//构造函数到时要根据order去改
	public LectureRepositoryMryImpl() {
		lecturePosts=Stream.of(
				new LecturePost("小陈1", "题目1","20010102", "地点1", "描述1", "备注还是描述","001"),
				new LecturePost("小陈2", "题目2","20020303", "地点2", "描述2", "备注还是描述","002"),
				new LecturePost("小陈3", "题目3","20020304", "地点3", "描述3", "备注还是描述","003"),
				new LecturePost("小陈4", "题目3","20020305", "地点3", "描述3", "备注还是描述","004"),
				new LecturePost("小陈5", "题目3","20020306", "地点3", "描述3", "备注还是描述","005"),
				new LecturePost("小陈6", "题目3","20020307", "地点3", "描述3", "备注还是描述","006"),
				new LecturePost("小陈7", "题目3","20020308", "地点3", "描述3", "备注还是描述","007")
				
				).collect(Collectors.toList());
	}
	
	
	//到时要结合order和start作分页,start从0开始
	@Override
	public Stream<LecturePost> getAllLecturePost(int start,int num) {
		// TODO Auto-generated method stub
		
		return lecturePosts.stream().skip(start).limit(num);
	}
	@Override
	public boolean removePost(String id) {
		
		lecturePosts.removeIf((p)->p.getId().equals(id));
		
		
		//持久化删除
		//coding...
		return true;
	}
	
	
	//顺序问题...到时插入到数据库得看看操作
	@Override
	public boolean addPost(LecturePost post) {
		
		
		lecturePosts.add(post);
		
		//持久化添加
		//coding..
		//然后刷新
		return true;
	}

	//所有的Search最后都要改为数据库的方式
	@Override
	public Optional<LecturePost> searchById(String id) {
		
		
		return lecturePosts.stream().filter(x->(x.getId()).equals(id)).findFirst();						//到时替换成数据库的搜索
		
	}


	@Override
	public Stream<LecturePost> searchByTime(String beginTime, String endTime) {
		// TODO Auto-generated method stub
		return lecturePosts.stream().filter(x->(x.getTime().compareTo(beginTime)>=0)&&(x.getTime().compareTo(endTime)<=0));
		 
	}


	@Override
	public Stream<LecturePost> searchByPresenter(String presenter) {
		// TODO Auto-generated method stub
		return lecturePosts.stream().filter(x->(x.getPresenter()).equals(presenter));
		 
	}
	

	
	public static void main(String[]args) {
		LectureRepository pLectureRepository=new LectureRepositoryMryImpl();
		pLectureRepository.addPost(new LecturePost("小陈1", "1", "20030305", "1","1", "1", "004"));
		pLectureRepository.removePost("002");
		Stream<LecturePost>p=pLectureRepository.getAllLecturePost(1,2);
//		for(LecturePost i:p) {
//			System.out.println(i);
//		}
		p.forEach(System.out::println);
		//List<LecturePost>p2=pLectureRepository.searchByPresenter("小陈1");
		Stream<LecturePost>p2=pLectureRepository.searchByTime("20010103","20030304");
//		LecturePost p3=pLectureRepository.searchById("003");
//		System.out.println(p3);
		p2.forEach(System.out::println);
		
	}
}
