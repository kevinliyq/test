package com.alu.study.collection;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyDoubleLinkedList<T> {
	private int capacity;
	private int count;
	private Node<T> head;
	private Node<T> tail;
	
	private static class Node<T>{
		private T data;
		private Node<T> next;
		private Node<T> previous;
		public Node(Node<T> next, T data, Node<T> previous){
			this.next = next;
			this.data = data;
			this.previous = previous;
		}
	}

	private ReentrantLock lock = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();

	public MyDoubleLinkedList(int capacity) {
		this.capacity = capacity;
	}

	public T put(T t) {
		lock.lock();
		try {
			if (count == capacity) {
				notFull.await();
			}
			Node<T> newNode;
			if(head == null){
				newNode = new Node<T>(null,t, null);
				head = newNode;
			}else{
				newNode = new Node<T>(tail,t, null);
				tail.next = newNode;
				
			}
			tail = newNode;
			count++;
			notEmpty.signal();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return t;
	}

	public T take() {
		lock.lock();
		try {
			while (count == 0) {
				notEmpty.await();
			}
			Node<T> node = head;
			Node<T> nextNode = head.next;
			nextNode.next = null;
			nextNode.previous = null;
			head =  nextNode;
			
			count--;
			notFull.signal();
			return node.data;
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		} finally {
			lock.unlock();
		}
	}

	public T peek() {
		return null;
	}

}
