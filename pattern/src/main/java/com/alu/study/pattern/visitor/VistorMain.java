package com.alu.study.pattern.visitor;

public class VistorMain {

	public static void main(String[] args) {
		Visitor visitor = new ConcreatElementAVisitor();
		IElement element = new ConcreatElementA();
		element.accept(visitor);
	}

}
