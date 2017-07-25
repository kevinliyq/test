package com.alu.study.pattern.visitor;

import java.util.concurrent.atomic.AtomicInteger;

public class ConcreatElementA implements IElement {
	private AtomicInteger index = new AtomicInteger(0);
	
	public void add(){
		index.addAndGet(1);
		System.out.println("Index:"+index.get());
	}
	public void accept(Visitor visitor) {
		visitor.visitMyData(this);
	}

}
