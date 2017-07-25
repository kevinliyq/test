package com.alu.study.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ItrTest {
	
	public static void main(String[] args){
//		ArrayList<String> l = new ArrayList<String>();
//		l.add("1");
//		l.add("3");
//		l.add("7");
//		l.add("6");
//		l.add("5");
//		l.add("4");
//		
//		Iterator<String> it = l.iterator();
//		while(it.hasNext()){
//			String val = it.next();
//			if("3".equals(val)){
//				it.remove();
//			}
//		}
//		System.out.println(l);
//		
//		l.listIterator();
//		
		List<Car> carList = new ArrayList<Car>();
		Car c1 = new Car("1","test","dazhong");
		Car c2 = new Car("2","test","dazhong");
		carList.add(c1);
		carList.add(c2);
		
		Iterator<Car> it = carList.iterator();
		while(it.hasNext()){
			Car car = it.next();
			car.setName("ppp");
		}
		
		System.out.println(carList.get(1).getName());
	}
}
		
	class Car{
		String id;
		String name;
		String brand;
		public Car(String id, String name, String brand) {
			super();
			this.id = id;
			this.name = name;
			this.brand = brand;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getBrand() {
			return brand;
		}
		public void setBrand(String brand) {
			this.brand = brand;
		}
		
		
	}

