package com.bbsbackend.components.idGenerator;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class SeqInteger implements IdGenerator{
	private final String name;
	private final AtomicInteger id=new AtomicInteger();
	public SeqInteger(String componentName) {
		this.name=componentName;
	}
	@Override
	public String generateId() {
		int result=id.getAndIncrement();
		return String.format("%08d", result);
	}

	@Override
	public String getName() {
		return name;
	}

    public static void main(String[] args) {
        IdGenerator g = IdGenerator.seqInteger("");
        System.out.println(g.generateId());
        System.out.println(g.generateId());
        System.out.println(g.generateId());
        System.out.println(g.generateId());
    }
}
