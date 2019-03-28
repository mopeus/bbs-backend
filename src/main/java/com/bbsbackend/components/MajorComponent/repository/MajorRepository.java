package com.bbsbackend.components.MajorComponent.repository;

import java.util.Optional;
import java.util.stream.Stream;

import com.bbsbackend.types.MajorValue;

public interface MajorRepository {
//	Stream<String> getAllSchools();
	Stream<MajorValue> getAllMajorValues();
	Stream<MajorValue> searchMajorValueBySchool(String school);
	Optional<MajorValue> searchMajorValueByCode(String code);
	Optional<MajorValue> searchMajorValueByInfo(String school,String major);
	boolean saveMajorValue(MajorValue majorValue);
}
