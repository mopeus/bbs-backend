package com.bbsbackend.components.MajorComponent.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.checkerframework.checker.units.qual.s;

import com.bbsbackend.types.MajorValue;

public class MajorRepositoryMryImpl implements MajorRepository{
	
	private List<MajorValue>majors=Stream.of(
			new MajorValue("专业1", "华工", "001"),
			new MajorValue("专业2", "华工", "002"),
			new MajorValue("专业3", "华工", "003"),
			new MajorValue("专业4", "华工", "004"),
			new MajorValue("专业5", "华工", "005"),
			new MajorValue("专业6", "华工", "006"),
			new MajorValue("专业7", "清华", "007"),
			new MajorValue("专业8", "清华", "008"),
			new MajorValue("专业9", "清华", "009")
			).collect(Collectors.toList());                      
	
	@Override
	public Stream<MajorValue> getAllMajorValues() {
		
		return majors.stream();
	}

	@Override
	public Stream<MajorValue> searchMajorValueBySchool(String school) {
		return majors.stream().filter(x->(x.getSchool()).equals(school));
	
	}

	@Override
	public Optional<MajorValue> searchMajorValueByCode(String code) {
		return majors.stream().filter(x->(x.getMajorCode()).equals(code)).findFirst();
	}
	
	@Override
	public Optional<MajorValue> searchMajorValueByInfo(String school,String major){
		return majors.stream().filter(x->(x.getSchool()).equals(school)&&(x.getMajor()).equals(major)).findFirst();	
	}
	
	@Override
	public boolean saveMajorValue(MajorValue majorValue) {
		// TODO Auto-generated method stub
		return majors.add(majorValue);
	}
	
}
