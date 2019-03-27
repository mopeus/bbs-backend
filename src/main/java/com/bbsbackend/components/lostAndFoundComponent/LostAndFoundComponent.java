package com.bbsbackend.components.lostAndFoundComponent;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bbsbackend.components.lostAndFoundComponent.repository.FoundRepositoryMryImpl;
import com.bbsbackend.components.lostAndFoundComponent.repository.LostAndFoundRepository;
import com.bbsbackend.components.lostAndFoundComponent.repository.LostRepositoryMryImpl;
import com.bbsbackend.types.LecturePost;
import com.bbsbackend.types.LostAndFoundInfo;
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
		lostRepository.savePost(post);
		return idtoString;
	}
	
	public String PubFound(String publisher,String obj,String time,String position,String description,String image,String contact) {
		UUID id=UUID.randomUUID();
		String idtoString=id.toString();
		LostAndFoundPost post=new LostAndFoundPost(idtoString, publisher, obj, time, position, description, image);
		foundRepository.savePost(post);
		return idtoString;	
		}
	
	public LostAndFoundInfo LostPostInfo(String id){
		
		Optional<LostAndFoundPost> post=lostRepository.searchById(id);
		
		if(post.isPresent()) {
			return new LostAndFoundInfo(post.get());
		}
		return null;
	}
	
	public LostAndFoundInfo FoundPostInfo(String id){
		
		Optional<LostAndFoundPost> post=foundRepository.searchById(id);
		
		if(post.isPresent()) {
			return new LostAndFoundInfo(post.get());
		}
		return null;

	}
	//filter代表是否过滤solved的帖子
	public Stream<String> AllFounds(int start,boolean filter){
		return foundRepository.getAllPosts(start, number,filter).map(x->x.getId());
	}
	

	public Stream<String> AllLosts(int start,boolean filter){
		return lostRepository.getAllPosts(start, number,filter).map(x->x.getId());
		
	}
	
	public boolean ClaimFound(String foundpostid,String claimant) {
		Optional<LostAndFoundPost>post=foundRepository.searchById(foundpostid);
		if(post.isPresent()) {
			post.get().setClaimant(claimant);
			post.get().setSolved(true);
			return foundRepository.updatePost(foundpostid, post.get());
		}
			
		return false;
	}
	
	public boolean ClaimLost(String lostpostid,String claimant) {
		Optional<LostAndFoundPost>post=lostRepository.searchById(lostpostid);
		if(post.isPresent()) {
			post.get().setClaimant(claimant);
			post.get().setSolved(true);
			return lostRepository.updatePost(lostpostid, post.get());
		}
			
		return false;
	}
	
	//老实说这个obj的模糊匹配，估计要放在数据库中
	public Stream<String> SearchLostByName(String obj){
		
		return lostRepository.searchByName(obj).map(x->x.getId());
	}
	
	public Stream<String> SearchFoundByName(String obj){
		return foundRepository.searchByName(obj).map(x->x.getId());
	}
	
	
	public Stream<String> SearchLostByTime(String beginTime,String endTime){
		return lostRepository.searchByTime(beginTime, endTime).map(x->x.getId());
	}
	
	public Stream<String> SearchFoundByTime(String beginTime,String endTime){
		return foundRepository.searchByTime(beginTime, endTime).map(x->x.getId());
	}
	
	public boolean RemoveLost(String id) {
		return lostRepository.remove(id);
	}
	
	public boolean RemoveFound(String id) {
		return foundRepository.remove(id);
	}
	
	public static void main(String[]args) {
		LostAndFoundComponent comp1=new LostAndFoundComponent(new LostRepositoryMryImpl(), new FoundRepositoryMryImpl());
		Stream<String> idList1=comp1.AllFounds(1, true);
		Stream<String> idList2=comp1.AllLosts(1, true);
		Cout(idList1);
		
		Cout(idList2);
		
		comp1.PubFound("新键作者", "找到物品1", "20190303", "地点1", "描述", "地址1", "联系方式1");
		comp1.PubLost("新键作者", "失去物品1", "20190303", "地点1", "描述", "地址1", "联系方式1");
		
		 idList1=comp1.AllFounds(0, true);
		 idList2=comp1.AllLosts(0, true);
		 Cout(idList1);
			
		 Cout(idList2);
		 
		 System.out.println("测试claim...");
		comp1.ClaimFound("003", "小城");
		comp1.ClaimLost("002", "小刚");
		
		 idList1=comp1.AllFounds(0, true);
		 idList2=comp1.AllLosts(0, true);
		 Cout(idList1);
		 Cout(idList2);
		
		 System.out.println("测试remove...");
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
		 
//		 idList1=comp1.FoundPostInfo(idList1.get(0));
//		 idList2=comp1.LostPostInfo(idList2.get(0));
//		 Cout(idList1);
//		 Cout(idList2);
//		 
	}
	
	public static void Cout(Stream<String>list) {
		list.forEach(System.out::println);
	}
}
