package com.alu.study.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MySingleLinkedList<T> {

	protected int count;
	protected Node<T> head;
	protected Node<T> tail;

	private int capacity;
	private ReentrantLock lock = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();

	private static class Node<T> {
		T data;
		Node<T> next;

		public Node(T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}

		public String toString() {
			return data.toString();
		}
	}

	public MySingleLinkedList(int capacity) {
		if (capacity < 1) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

	public T addLast(T t) {
		Node<T> newNode = new Node<T>(t, null);
		lock.lock();
		try {
			if(capacity == count){
				throw new IllegalStateException("queue is full");
			}
			
			if (head == null) {
				head = newNode;
				tail = newNode;
			} else {
				tail.next = newNode;
				tail = newNode;
			}
			count++;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
		
		return t;
	}
	
	/**
	 * block method
	 * @param t
	 * @return
	 */
	public T put(T t) {
		Node<T> newNode = new Node<T>(t, null);
		lock.lock();
		try {
			while(capacity == count){
					notFull.await();
			}
			
			if (head == null) {
				head = newNode;
				tail = newNode;
			} else {
				tail.next = newNode;
				tail = newNode;
			}
			count++;
			notEmpty.signal();
		}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return t;
	}

	public T removeFirst() {
		T result = null;
		lock.lock();
		try {
			if (head == null) {
				throw new NoSuchElementException();
			} else {
				result = head.data;
				head = head.next;
			}
			count--;
		} finally {
			lock.unlock();
		}
		return result;
	}
	
	/**
	 * blocking method
	 * @return
	 */
	public T take() {
		T result = null;
		lock.lock();
		try {
			while(head == null){
				notEmpty.await();
			}
			
			result = head.data;
			head = head.next;
			count--;
			notFull.signal();
		}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} finally {
			System.out.println(Thread.currentThread().getName()+":"+result + " is taken");
			lock.unlock();
		}
		return result;
	}

	public T peek() {
		if (head == null) {
			throw new NoSuchElementException();
		} else {
			return head.data;
		}
	}

	public int size() {
		return count;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public Iterator<T> iterator() {
		return new Iter();
	}

	public void reserver() {
		if (count < 2) {
			return;
		}
		Node<T> p = head;
		Node<T> p2 = p.next;
		Node<T> lastReserveNode = null;
		int i = 0;
		while (p2 != null) {
			if (i++ == 0) {
				p.next = null;
			} else {
				p.next = lastReserveNode;
			}
			lastReserveNode = p;
			p = p2;
			p2 = p2.next;
		}

		p.next = lastReserveNode;
		Node<T> temp;
		temp = head;
		head = tail;
		tail = temp;

	}

	private class Iter implements Iterator<T> {
		private int cursor = -1;
		private Node<T> lastRetNode = null;

		public boolean hasNext() {
			return cursor != count - 1;
		}

		public T next() {
			if (count == 0) {
				throw new NoSuchElementException();
			}
			if (cursor == -1) {
				lastRetNode = head;
			} else {
				lastRetNode = lastRetNode.next;
			}
			cursor++;

			if (lastRetNode != null) {
				return lastRetNode.data;
			} else {
				return null;
			}
		}

		public void remove() {
			throw new NotSupportedException();
		}

	}
}

class NotSupportedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
