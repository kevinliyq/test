package com.alu.study.collection;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MySingleLinkedListTest {
	private MySingleLinkedList<Integer> list;
	
	@Before
	public void setUp() throws Exception {
		list = new MySingleLinkedList<Integer>(12);
	}

	@After
	public void tearDown() throws Exception {
		list = null;
	}

	@Test
	public void testAddFirst() {
		assertEquals(8,list.addLast(8).intValue());
		assertEquals(6,list.addLast(6).intValue());
		assertEquals(12,list.addLast(12).intValue());
		assertEquals(3,list.addLast(3).intValue());
		assertTrue(4 == list.size());
		assertFalse(list.isEmpty());
		assertEquals(8,list.removeFirst().intValue());
		assertTrue(3 == list.size());
		for(Iterator<Integer> it = list.iterator(); it.hasNext();){
			System.out.print(it.next()+" ");
		}
		System.out.println("");
		list.reserver();
		System.out.println("After reserve:");
		for(Iterator<Integer> it = list.iterator(); it.hasNext();){
			System.out.print(it.next()+" ");
		}
	}
	@Test
	public void testReserver() {
		for(int i = 1; i < 3; i++){
			list.addLast(i);
		}
		list.reserver();
		
		System.out.println("After reserve:");
		for(Iterator<Integer> it = list.iterator(); it.hasNext();){
			System.out.print(it.next()+" ");
		}
	}
	
	@Test(timeout= 50000)
	public void testProducerAndConsumer(){
		byte[] lock = new byte[0];
		Thread producer = new Thread(new Producer(100));
		for(int i = 1; i < 4; i++){
			Thread consumer = new Thread(new Consumer("consumer"+i,lock));
			consumer.start();
		}
		
		producer.start();
		
		synchronized(lock){
			try {
				lock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	class Producer implements Runnable{
		private int tickets;
		
		public Producer(int tickets){
			this.tickets = tickets;
		}
		public void run() {
			while(tickets > 0){
				System.out.println("Produce ticket:"+tickets);
				list.put(tickets--);
			}
		}
		
	}
	
	class Consumer implements Runnable{
		private byte[] lock;
		private String message;
		
		public Consumer(String message, byte[] lock) {
			this.message = message;
			this.lock = lock;
		}

		public void run() {
			while(true){
				int ticket = list.take();
				System.out.println(message+" Get ticket:"+ticket);
				if (ticket <= 1){
					synchronized(lock){
						lock.notify();
					}
					break;
				}
			}
		}
		
	}
}
