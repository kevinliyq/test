package com.alu.study.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;

public class MyArrayStack<T> implements Stack<T> {
	
	protected Object[] items;
	protected int top = -1;
	protected int count = 0;
	
	private int capacity = 12;
	protected int modCount = 0;
	protected ReentrantLock lock = new ReentrantLock();
	
	public MyArrayStack(){
		this.items = new Object[capacity];
	}
	
	public MyArrayStack(int capacity){
		if(capacity < 1){
			throw new IllegalArgumentException("capacity is less 1");
		}
		this.capacity = capacity;
		this.items = new Object[capacity];
	}
	
	public T push(T e) {
		lock.lock();
		try {
			items[++top] = e;
			count++;
			modCount++;
			if (top == items.length - 1) {
				resize();
			}
			return e;
		} finally {
			lock.unlock();
		}
		
	}
	public T pop() {
		lock.lock();
		try{
			if(count == 0){
				return null;
			}
			count--;
			modCount++;
			return cast(items[top--]);
		}finally{
			lock.unlock();
		}
	}
	
	@SuppressWarnings("unused")
	private T cast(Object e){
		return (T)e;
	}


	private void resize() {
		capacity *= 2;
		Object[] temp = new Object[capacity];
		System.arraycopy(items, 0, temp, 0, items.length);
		items = temp;
	}	
	
	public int size() {
		lock.lock();
		try {
			return count;
		} finally {
			lock.unlock();
		}
	}

	public boolean contains(T e) {
		if(e == null){
			return false;
		}
		lock.lock();
		try{
			if(count == 0){
				return false;
			}
			for(int i = top; i>=0; i--){
				T t = cast(items[i]);
				if(e.equals(t)){
					return true;
				}
			}
		}finally{
			lock.unlock();
		}
		return false;
	}

	public T peek() {
		lock.lock();
		try{
			return (T) (count == 0 ? null : cast(items[top]));
		}finally{
			lock.unlock();
		}
	}
	public boolean isEmpty() {
		lock.lock();
		try{
			return count == 0;
		}finally{
			lock.unlock();
		}
	}
	public Iterator<T> iterator() {
		return new Iter();
	}
	
	private class Iter implements Iterator<T>{

		protected int cursor = top;
		protected int lastRetIndex = -1;
		
		protected int expectedModCount = modCount;
		
		public boolean hasNext() {
			return cursor != -1;
		}

		public T next() {
			MyArrayStack.this.lock.lock();
			try {
				checkConcurrentModification();
				if (cursor < 0) {
					throw new NoSuchElementException();
				}
				T t = (T)items[cursor];
				lastRetIndex = cursor;
				cursor--;
				
				return t;
			} finally {
				MyArrayStack.this.lock.unlock();
			}
		}

		public void remove() {
			MyArrayStack.this.lock.lock();
			try{
				checkConcurrentModification();
				if (lastRetIndex == -1){
					throw new IllegalStateException();
				}
				remove(lastRetIndex);
				expectedModCount = modCount;
			}finally{
				MyArrayStack.this.lock.unlock();
			}
		}
		
		protected void checkConcurrentModification() {
			if(expectedModCount != MyArrayStack.this.modCount){
				System.out.println("expectedModCount="+expectedModCount+",modCount="+MyArrayStack.this.modCount);
				throw new ConcurrentModificationException();
			}
			
		}
		
		private void remove(int lastRetIndex) {
			MyArrayStack<T> target = MyArrayStack.this;
			
			if(lastRetIndex == target.top){
				target.pop();
			}else{
				for(int i = lastRetIndex+1; i <= top; i++){
					target.items[i-1] = target.items[i];
				}
				target.pop();
			}
			
			
		}
		
	}
	
	public ListIterator<T> listIterator() {
		return new MyListIterator(0);
	}
	private class MyListIterator extends Iter implements ListIterator<T>{
		private int index = 0;
		
		public MyListIterator(int index){
			super();
			this.index = index;
		}
		
		public boolean hasPrevious() {
			return cursor < top;
		}

		public T previous() {
			lock.lock();
			try{
				if(cursor == top){
					throw new IllegalStateException();
				}
				checkConcurrentModification();
				T t =  (T)items[++cursor];
				lastRetIndex = cursor;
				return t;
			}finally{
				lock.unlock();
			}
		}

		public int nextIndex() {
			return cursor;
		}

		public int previousIndex() {
			return cursor+1;
		}		

		public void set(T e) {
			lock.lock();
			try{
				if(lastRetIndex == -1 || lastRetIndex > top){
					throw new IllegalStateException();
				}
				checkConcurrentModification();
				items[lastRetIndex] = e;
				modCount++;
				expectedModCount = modCount; 
			}finally{
				lock.unlock();
			}
		}

		public void add(T e) {
			lock.lock();
			try{
				checkConcurrentModification();
				if(cursor < 0 || cursor > top){
					throw new IllegalStateException();
				}
				if(cursor == top){
					MyArrayStack.this.push(e);
				}else{
					for(int i = top; i>= lastRetIndex;i--){
						items[i+1] = items[i];
					}
					items[lastRetIndex] = e;
					top++;
				}
				modCount++;
				expectedModCount = modCount;
			}finally{
				lock.unlock();
			}
			
		}
		
	}

	public static void main(String[] args){
		Stack<Integer> stack = new MyArrayStack<Integer>();
		stack.push(1);
		stack.push(3);
		stack.push(8);
		stack.push(2);
		
		System.out.println(4 == stack.size());
		System.out.println(2 == stack.peek());
		System.out.println(stack.isEmpty() == false);
		
		System.out.println(2 == stack.pop());
		System.out.println(3 == stack.size());
		
		Iterator<Integer> it = stack.iterator();
		int i = 0;
		while(it.hasNext()){
			System.out.println(it.next());
			if(i == 1){
				it.remove();
			}
			i++;
			
		}
		System.out.println("size="+stack.size());
		System.out.println("-----------");
		Iterator<Integer> it2 = stack.iterator();
		while(it2.hasNext()){
			System.out.println(it2.next());
		}
		System.out.println(stack.contains(3) == false);
	}
}
