package com.bbsbackend.components.lostAndFoundComponent.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.security.auth.x500.X500Principal;

import com.bbsbackend.types.LostAndFoundPost;
/*
 * save updatePost 参数保证不为空
 */
public class LostRepositoryMryImpl implements LostAndFoundRepository{
	private  List<LostAndFoundPost>lostPosts	;
	public LostRepositoryMryImpl() {
		// TODO Auto-generated constructor stub
		lostPosts=Stream.of(
				new LostAndFoundPost("001", "作者1", "物品1", "20090101", "C12", "惨啊", "图片地址1"),
				new LostAndFoundPost("002", "作者2", "物品2", "20090102", "C12", "惨啊", "图片地址2"),
				new LostAndFoundPost("003", "作者3", "物品2", "20090103", "C12", "惨啊", "图片地址3"),
				new LostAndFoundPost("004", "作者4", "物品4", "20090104", "C12", "惨啊", "图片地址4"),
				new LostAndFoundPost("005", "作者5", "物品5", "20090105", "C13", "惨啊", "图片地址5"),
				new LostAndFoundPost("006", "作者6", "物品6", "20090106", "C13", "惨啊", "图片地址6"),
				new LostAndFoundPost("007", "作者7", "物品7", "20090111", "C15", "惨啊", "图片地址7")
				).collect(Collectors.toList());
	}
	@Override
	public boolean savePost(LostAndFoundPost post) {
		//持久化操作
		return lostPosts.add(post);
	}
	
//到时会改成持久化操作
	public boolean updatePost(String id,LostAndFoundPost newpost) {
		Optional<LostAndFoundPost> post1=lostPosts.stream().filter(x->(x.getId()).equals(id)).findFirst();
		if(post1.isPresent()&&newpost!=null) {
			post1.get().update(newpost);
			return true;
		}
		return false;
	}
	

	@Override
	public Stream<LostAndFoundPost> getAllPosts(int start,int num,boolean filter) {
		if(filter==false) {
			return lostPosts.stream().skip(start).limit(num);
		}
		else {
			return	lostPosts.stream().filter(x->(x.getSolved())==false).skip(start).limit(num); 
		}
	}


	@Override
	public boolean remove(String id) {
		
		return	lostPosts.removeIf((p)->p.getId().equals(id));

		//持久化删除
		//coding...
	}

	
	//要替换成数据库的搜索操作
	@Override
	public Optional<LostAndFoundPost> searchById(String id) {
		
		return lostPosts.stream().filter(x->(x.getId()).equals(id)).findFirst();
	}
	
	
	@Override
	public Stream<LostAndFoundPost> searchByName(String obj) {

		return lostPosts.stream().filter(x->(x.getObj()).equals(obj));
	}

	@Override
	public Stream<LostAndFoundPost> searchByTime(String beginTime, String endTime) {
		return lostPosts.stream().filter(x->(x.getTime().compareTo(beginTime)>=0)&&(x.getTime().compareTo(endTime)<=0));		
	}

	public static void main(String[]args) {
		LostRepositoryMryImpl repositoryMryImpl=new LostRepositoryMryImpl();
		repositoryMryImpl.updatePost("002",new LostAndFoundPost("006", "作者6", "物品6", "20090106", "C13", "惨啊11", "图片地址998"));
	}

}
