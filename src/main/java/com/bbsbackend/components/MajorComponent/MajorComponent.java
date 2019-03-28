package com.bbsbackend.components.MajorComponent;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import com.bbsbackend.components.MajorComponent.repository.MajorRepository;
import com.bbsbackend.components.MajorComponent.repository.MajorRepositoryMryImpl;
import com.bbsbackend.types.MajorValue;
/*
 * 决定将Majorcode也封装在MajorValue里面，然后MryImpl底层是用List而不是Map
 * 因为最后数据库的存储应该时 code school value 分成三个字段放在同一个数据库中，所以最后搜索只要找到对应的几行，然后包装成一个个MajorValue就可
 */
public class MajorComponent {
	//一个search方法
	private MajorRepository majorRepository;
	public MajorComponent(MajorRepository majorRepository) {
		this.majorRepository=majorRepository;
	}
	public Optional<MajorValue> GetMajorValue(String school,String major) {
		
		return majorRepository.searchMajorValueByInfo(school, major);
	}
	
	//统一用searchMajorVaSlue
	public Stream<String> AllSchool(){
		return majorRepository.getAllMajorValues().map(x->x.getSchool()).distinct();
	}
	
	public Stream<String> AllMajors(){
		return majorRepository.getAllMajorValues().map(x->x.getMajor()).distinct();
	}
	
	//searchMajorValuewithSchool
	public Stream<String> MajorsIn(String school){
		return majorRepository.searchMajorValueBySchool(school).map(x->x.getMajor());
	}
	
	
	//觉得只生成code很奇怪,顺便加上save语句好了
	public String GenerateMajorCode(MajorValue majorValue) {
		UUID majorCode=UUID.randomUUID();
		majorValue.setMajorCode(majorCode.toString());
		majorRepository.saveMajorValue(majorValue);
		return majorCode.toString();
	}
	
	//searchMajorValuewithCode
	public Optional<MajorValue> GetMajorValueFromCode(String majorCode) {
		return majorRepository.searchMajorValueByCode(majorCode);
	}
	
	public static void main(String[]args) {
		MajorComponent majorComponent=new MajorComponent(new MajorRepositoryMryImpl());
		majorComponent.AllMajors().forEach(System.out::println);;
		majorComponent.AllSchool().forEach(System.out::println);
		majorComponent.MajorsIn("清华").forEach(System.out::println);
		MajorValue m1=new MajorValue("复旦", "电竞");
		System.out.println("***");
		String idString=majorComponent.GenerateMajorCode(m1);
		System.out.println(idString);
		System.out.println(majorComponent.GetMajorValue("复旦","电竞").get().getMajorCode());
		System.out.println(majorComponent.GetMajorValueFromCode(idString).get().getMajor());
		
	}
}
