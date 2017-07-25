package com.alu.study.collection;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyLinkedStack<T> implements Stack<T>{
	
	private static class Node {
		Object data;
		Node next;
		Node previous;
		Node(Object data,Node next, Node prevous){
			this.data = data;
			this.next = next;
			this.previous = previous;
		}		
	};
	
	private Node head;
	private Node tail;
	
	private int count;
	private int capacity;
	
	private ReentrantLock lock = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();
	
	public MyLinkedStack(int capacity){
		if(capacity < 1){
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

	public T push(T e) {
		lock.lock();
		try {
			while(count == capacity){
					notFull.await();
			}
			Node next = null;
			if (head != null) {
				next = head.next;
			}
			Node previous = null;

			Node node = new Node(e, next, previous);
			head.previous = node;
			head = node;
			notEmpty.signalAll();
			return e;
			} catch (InterruptedException e1) {
				return null;
			}
			finally {
			lock.unlock();
		}
		
	}

	public T pop() {
		if(count == 0){
			return null;
		}
		return null;
	}
	
	static <T> T cast(Object o){
		return (T)o;
	}

	public T peek() {
		// TODO Auto-generated method stub
		return null;
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean contains(T e) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
