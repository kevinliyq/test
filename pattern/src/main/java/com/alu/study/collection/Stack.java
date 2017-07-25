package com.alu.study.collection;

import java.util.Iterator;
import java.util.ListIterator;

public interface Stack<T>{

	public T push(T e);
	
	public T pop();
	
	public T peek();
	
	public int size();
	
	public boolean contains(T e);
	
	public boolean isEmpty();
	
	public Iterator<T> iterator();
	
	public ListIterator<T> listIterator();
}
