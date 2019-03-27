package com.bbsbackend.components.lectureComponent;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.bbsbackend.components.lectureComponent.repository.LectureRepository;
import com.bbsbackend.components.lectureComponent.repository.LectureRepositoryMryImpl;
import com.bbsbackend.types.LectureInfo;
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
	public Stream<String> AllLecture(int start) throws Exception{
		if(start<0)
			throw new Exception();
		return lectureRepository.getAllLecturePost(start, number).map(x->x.getId());
	}
	
	//SearchByID  改！！！！
	public LectureInfo LectureInfo(String id){
		Optional<LecturePost> post=lectureRepository.searchById(id);
		if(post.isPresent()) {
			return new LectureInfo(post.get());
		}
		return null;
	}
	
	public Stream<String> SearchByTime(String beginTime,String endTime){
		return lectureRepository.searchByTime(beginTime, endTime).map(x->x.getId());
	}
	
	public Stream<String> SearchByPresenter(String presenter){
		return lectureRepository.searchByPresenter(presenter).map(x->x.getId());
	}
	
	public boolean Remove(String id) {
		
		return lectureRepository.removePost(id);
	}
	
	
	public static void main(String[]args) throws Exception {
		LectureRepository lectureRepository=new LectureRepositoryMryImpl();
		LectureComponent lectureComponent=new LectureComponent(lectureRepository);
		Stream<String> p1=lectureComponent.AllLecture(0);
		p1.forEach(System.out::println);
		
		System.out.println("测试PostLecture");
		
		lectureComponent.PostLecture("新建作者", "新建标题", "20190314", "广州","描述嗷", "不知道什么");
		lectureComponent.PostLecture("新建作者", "新建标题", "20190314", "广州","描述嗷2", "不知道什么2");
		p1=lectureComponent.AllLecture(1);
		p1.forEach(System.out::println);
		
		System.out.println("测试SearchByTime");
		Stream<String> ids1=lectureComponent.SearchByTime("20010103", "20190314");
		ids1.forEach(System.out::println);
		
		System.out.println("测试SearchByPresenter");
		Stream<String> ids=lectureComponent.SearchByPresenter("新建作者");
		ids.forEach(System.out::println);
		
		System.out.println("测试LectureInfo");
		LectureInfo info=lectureComponent.LectureInfo("004");
		System.out.println(info);
	}
}
