package com.bbsbackend.components.lectureComponent;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.bbsbackend.components.lectureComponent.repository.LectureRepository;
import com.bbsbackend.components.lectureComponent.repository.LectureRepositoryMryImpl;
import com.bbsbackend.types.LecturePost;

/*
 * 这个 id的设置还有start和number的设置要再商榷
 */
@Component
public class LectureComponent {
	private final LectureRepository lectureRepository;
	
	private static final int number=10;
	
	public LectureComponent(LectureRepository lectureRepository) {
		this.lectureRepository=lectureRepository;
	}
	
	
	public String PostLecture(String presenter,String title,String time,String position,String description,String remark) {
		UUID id=UUID.randomUUID();
		String idtoString=id.toString();
		LecturePost post=new LecturePost(presenter, title, time, position, description, remark, idtoString);
		lectureRepository.addPost(post);
		return idtoString;
	}
	
	//这个参数不知道什么
	//start从0开始
	public List<String> AllLecture(int start) throws Exception{
		if(start<0)
			throw new Exception();
		List<LecturePost>posts=lectureRepository.getAllLecturePost(start, number);
		List<String>idStrings= posts.stream().map(x->x.getId()).collect(Collectors.toList());
		return idStrings;
	}
	
	//SearchByID
	public List<String> LectureInfo(String id){
		LecturePost post=lectureRepository.searchById(id);
		List<String> info=Stream.of(post.getPresenter(),post.getTitle(),post.getTime(),post.getPosition(),post.getDescription(),post.getRemark()).collect(Collectors.toList());
		return info;
	}
	
	public List<String> SearchByTime(String beginTime,String endTime){
		List<LecturePost>posts=lectureRepository.searchByTime(beginTime, endTime);
		List<String>idList=posts.stream().map(x->x.getId()).collect(Collectors.toList());
		return idList;
	}
	
	public List<String> SearchByPresenter(String presenter){
		List<LecturePost>posts=lectureRepository.searchByPresenter(presenter);
		List<String>idList=posts.stream().map(x->x.getId()).collect(Collectors.toList());
		return idList;
	}
	
	public boolean Remove(String id) {
		
		return lectureRepository.removePost(id);
	}
	
	
	public static void main(String[]args) throws Exception {
		LectureRepository lectureRepository=new LectureRepositoryMryImpl();
		LectureComponent lectureComponent=new LectureComponent(lectureRepository);
		List<String> p1=lectureComponent.AllLecture(0);
		for(String i:p1)
			System.out.println(i);
		
		
		System.out.println("测试PostLecture");
		
		lectureComponent.PostLecture("新建作者", "新建标题", "20190314", "广州","描述嗷", "不知道什么");
		lectureComponent.PostLecture("新建作者", "新建标题", "20190314", "广州","描述嗷2", "不知道什么2");
		p1=lectureComponent.AllLecture(1);
		for(String i:p1)
			System.out.println(i);
		
		
		System.out.println("测试SearchByTime");
		List<String> ids1=lectureComponent.SearchByTime("20010103", "20190314");
		for(String i:ids1)
			System.out.println(i);
		
		
		System.out.println("测试SearchByPresenter");
		List<String> ids=lectureComponent.SearchByPresenter("新建作者");
		for(String i:ids)
			System.out.println(i);
		
		System.out.println("测试LectureInfo");
		List<String> info=lectureComponent.LectureInfo(ids.get(0));
		for(String i:info)
			System.out.println(i);
	}
}
