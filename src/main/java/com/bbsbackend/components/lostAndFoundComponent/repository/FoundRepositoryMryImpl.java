package com.bbsbackend.components.lostAndFoundComponent.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import com.bbsbackend.types.LostAndFoundPost;
/*
 * 数据库中每一项都不能为空
 */
public class FoundRepositoryMryImpl implements LostAndFoundRepository{
	private  List<LostAndFoundPost>foundPosts;
	public FoundRepositoryMryImpl() {
		// TODO Auto-generated constructor stub
		foundPosts=Stream.of(
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
	public boolean publish(LostAndFoundPost post) {
		//持久化操作
		foundPosts.add(post);
		return true;
	}


	@Override
	public List<LostAndFoundPost> getAllPosts(int start,int num,boolean filter) {
		
		if(filter==false) {
			return foundPosts.stream().skip(start).limit(num).collect(Collectors.toList());
		}
		else {
			return	foundPosts.stream().filter(x->(x.getSolved())==false).skip(start).limit(num).collect(Collectors.toList()); 
		}
	}


	@Override
	public boolean remove(String id) {
		
		foundPosts.removeIf((p)->p.getId().equals(id));
		
		
		//持久化删除
		//coding...
		return true;
	}

	
	//要替换成数据库的搜索操作
	@Override
	public LostAndFoundPost searchById(String id) {
		
		Optional<LostAndFoundPost> posts=foundPosts.stream().filter(x->(x.getId()).equals(id)).findFirst();
		return posts.orElse(null);
	}
	
	
	@Override
	public List<LostAndFoundPost> searchByName(String obj) {
		// TODO Auto-generated method stub
		
		//万一filter都不符合？
		List<LostAndFoundPost> posts=foundPosts.stream().filter(x->(x.getObj()).equals(obj)).collect(Collectors.toList());
		
		return posts;
	}

	@Override
	public List<LostAndFoundPost> searchByTime(String beginTime, String endTime) {
		List<LostAndFoundPost> posts=foundPosts.stream().filter(x->(x.getTime().compareTo(beginTime)>=0)&&(x.getTime().compareTo(endTime)<=0)).collect(Collectors.toList());
		
		return posts;
	}
	
	@Override
	public boolean claim(String id, String claimant) {
		//持久化更新
		for(LostAndFoundPost post:foundPosts) {
			if((post.getId()).equals(id)) {
				post.setSolved(true);
				post.setClaimant(claimant);
			}
		}
		return false;
	}

}
