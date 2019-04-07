package com.bbsbackend.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {
	public static <T> List<T> buildList(T ... Objs){
		return Stream.of(Objs).collect(Collectors.toList());
	}
}
