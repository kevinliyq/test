package com.alu.study.pattern.visitor;

public class ConcreatElementAVisitor implements Visitor {

	public void visitMyData(ConcreatElementA element) {
		element.add();
	}

}
