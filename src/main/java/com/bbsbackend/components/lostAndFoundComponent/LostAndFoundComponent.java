package com.bbsbackend.components.lostAndFoundComponent;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bbsbackend.components.lostAndFoundComponent.repository.FoundRepositoryMryImpl;
import com.bbsbackend.components.lostAndFoundComponent.repository.LostAndFoundRepository;
import com.bbsbackend.components.lostAndFoundComponent.repository.LostRepositoryMryImpl;
import com.bbsbackend.types.LecturePost;
import com.bbsbackend.types.LostAndFoundPost;
/*
 * 某些查找方法可能会返回空list，到时用 isEmpty方法即可
 * start从0开始
 */
public class LostAndFoundComponent {
	private final LostAndFoundRepository lostRepository;
	private final LostAndFoundRepository foundRepository;
	private static final int number=10;
	public LostAndFoundComponent(LostAndFoundRepository lostRepository,LostAndFoundRepository foundRepository) {
		this.lostRepository=lostRepository;
		this.foundRepository=foundRepository;	
	}
	//image为地址 description是长文本，可能也是地址
	public String PubLost(String publisher,String obj,String time,String position,String description,String image,String contact) {
		UUID id=UUID.randomUUID();
		String idtoString=id.toString();
		LostAndFoundPost post=new LostAndFoundPost(idtoString, publisher, obj, time, position, description, image);
		lostRepository.publish(post);
		return idtoString;
	}
	
	public String PubFound(String publisher,String obj,String time,String position,String description,String image,String contact) {
		UUID id=UUID.randomUUID();
		String idtoString=id.toString();
		LostAndFoundPost post=new LostAndFoundPost(idtoString, publisher, obj, time, position, description, image);
		foundRepository.publish(post);
		return idtoString;	
		}
	
	public List<String> LostPostInfo(String id){
		
		LostAndFoundPost post=lostRepository.searchById(id);
		
		if(post!=null) {
			List<String> info=Stream.of(post.getId(),post.getPublisher(),post.getObj(),post.getTime(),post.getPosition(),post.getDescription(),post.getImage(),post.getClaimant()).collect(Collectors.toList());
			return info;
		}
		return null;
	}
	
	public List<String> FoundPostInfo(String id){
		
		LostAndFoundPost post=foundRepository.searchById(id);
		
		if(post!=null) {
			List<String> info=Stream.of(post.getId(),post.getPublisher(),post.getObj(),post.getTime(),post.getPosition(),post.getDescription(),post.getImage(),post.getClaimant()).collect(Collectors.toList());
			return info;
		}
		return null;

	}
	//filter代表是否过滤solved的帖子
	public List<String> AllFounds(int start,boolean filter){
		List<LostAndFoundPost>posts=foundRepository.getAllPosts(start, number,filter);
		List<String>idList=posts.stream().map(x->x.getId()).collect(Collectors.toList());
		return idList;
	}
	

	public List<String> AllLosts(int start,boolean filter){
		List<LostAndFoundPost>posts=lostRepository.getAllPosts(start, number,filter);
		List<String>idList=posts.stream().map(x->x.getId()).collect(Collectors.toList());
		return idList;
		
	}
	
	public boolean ClaimFound(String foundpostid,String claimant) {
		foundRepository.claim(foundpostid, claimant);
		return true;
	}
	
	public boolean ClaimLost(String lostpostid,String claimant) {
		lostRepository.claim(lostpostid, claimant);
		return true;
	}
	
	//老实说这个obj的模糊匹配，估计要放在数据库中
	public List<String> SearchLostByName(String obj){
		//null?
		List<LostAndFoundPost>posts=lostRepository.searchByName(obj);
		List<String>idList=posts.stream().map(x->x.getId()).collect(Collectors.toList());
		return idList;
	}
	
	public List<String> SearchFoundByName(String obj){
		List<LostAndFoundPost>posts=foundRepository.searchByName(obj);
		List<String>idList=posts.stream().map(x->x.getId()).collect(Collectors.toList());
		return idList;
	}
	
	
	public List<String> SearchLostByTime(String beginTime,String endTime){
		List<LostAndFoundPost>posts=lostRepository.searchByTime(beginTime, endTime);
		List<String>idList=posts.stream().map(x->x.getId()).collect(Collectors.toList());
		return idList;
	}
	
	public List<String> SearchFoundByTime(String beginTime,String endTime){
		List<LostAndFoundPost>posts=foundRepository.searchByTime(beginTime, endTime);
		List<String>idList=posts.stream().map(x->x.getId()).collect(Collectors.toList());
		return idList;
	}
	
	public boolean RemoveLost(String id) {
		return lostRepository.remove(id);
	}
	
	public boolean RemoveFound(String id) {
		return foundRepository.remove(id);
	}
	
	public static void main(String[]args) {
		LostAndFoundComponent comp1=new LostAndFoundComponent(new LostRepositoryMryImpl(), new FoundRepositoryMryImpl());
		List<String> idList1=comp1.AllFounds(1, true);
		List<String> idList2=comp1.AllLosts(1, true);
		Cout(idList1);
		
		Cout(idList2);
		
		comp1.PubFound("新键作者", "找到物品1", "20190303", "地点1", "描述", "地址1", "联系方式1");
		comp1.PubLost("新键作者", "失去物品1", "20190303", "地点1", "描述", "地址1", "联系方式1");
		
		 idList1=comp1.AllFounds(0, true);
		 idList2=comp1.AllLosts(0, true);
		 Cout(idList1);
			
		 Cout(idList2);
		 
		comp1.ClaimFound("003", "小城");
		comp1.ClaimLost("002", "小刚");
		
		 idList1=comp1.AllFounds(0, true);
		 idList2=comp1.AllLosts(0, true);
		 Cout(idList1);
		 Cout(idList2);
		
		
		comp1.RemoveFound("002");
		comp1.RemoveLost("003");
		idList1=comp1.SearchFoundByName("物品2");
		idList2=comp1.SearchLostByName("物品2");
		
		 Cout(idList1);
		 Cout(idList2);
	System.out.println("******");
	//002之前被删了
		idList1=comp1.SearchFoundByTime("20090102", "20190303");
		idList2=comp1.SearchLostByTime("20090102", "20190303");
		 Cout(idList1);
		 Cout(idList2);
		 
		 idList1=comp1.FoundPostInfo(idList1.get(0));
		 idList2=comp1.LostPostInfo(idList2.get(0));
		 Cout(idList1);
		 Cout(idList2);
		 
	
	}
	
	public static void Cout(List<String>list) {
		for(String i:list)
		
			System.out.println(i);
	}
}
