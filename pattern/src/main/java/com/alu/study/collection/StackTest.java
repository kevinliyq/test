package com.alu.study.collection;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.ListIterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StackTest {
	private Stack<Integer> stack;
	@Before
	public void setUp() throws Exception {
		stack = new MyArrayStack<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		stack = null;
	}

	@Test
	public void testPush() {
		assertEquals("Push is not equal",10,stack.push(10).intValue());
		assertFalse(stack.isEmpty());
		assertTrue(stack.contains(10));
		assertEquals(10,stack.pop().intValue());
	}

	@Test
	public void testIterator() {
		stack.push(10);
		stack.push(3);
		stack.push(8);
		Iterator<Integer> it = stack.iterator();
		it.hasNext();
		assertEquals("",8,it.next().intValue());
		assertEquals("",3,it.next().intValue());
		assertEquals("",10,it.next().intValue());
		assertFalse(it.hasNext());
	}
	
	@Test
	public void testListIterator() {
		stack.push(10);
		stack.push(3);
		stack.push(8);
		ListIterator<Integer> it = stack.listIterator();
		
		it.hasNext();
		assertEquals("",8,it.next().intValue());
		
		assertEquals("",3,it.next().intValue());
		
		assertTrue(it.hasPrevious());
		assertEquals("",3,it.previous().intValue());
		
		assertTrue(it.hasNext());
		assertEquals("",8,it.previous().intValue());
		assertEquals("",8,it.next().intValue());
		System.out.println("nextIndex="+it.nextIndex());
		System.out.println("previousIndex="+it.previousIndex());
		System.out.println(it.next());
	}
	@Test
	public void testListIteratorNextPrevious() {
		stack.push(8);
		stack.push(3);
		stack.push(10);
		
		ListIterator<Integer> it = stack.listIterator();
		
		
		assertEquals("",10,it.next().intValue());
		assertEquals("",3,it.next().intValue());
		it.set(12);
		assertEquals("",12,it.previous().intValue());
		it.add(15);
		
		for(Iterator it2 = stack.iterator(); it2.hasNext();){
			System.out.println(it2.next());
		}
		
	}

}
