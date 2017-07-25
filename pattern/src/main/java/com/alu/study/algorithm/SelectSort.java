package com.alu.study.algorithm;


public class SelectSort {

	public static void main(String[] args){
		int[] a = {11, 8, 15, 3, 8, 9, 2, 6, 4, 7, 1};
		for(int i = 0; i < a.length; i++){
			int min = a[i];
			int index = i;
			for(int j = i+1; j < a.length; j++){
				if (a[j] < min){
					index = j;
					min = a[index];
				}
			}
			if(i != index){
				int temp = a[i];
				a[i] = a[index];
				a[index] = temp;
			}
		}
		for(int i = 0; i < a.length; i++){
			System.out.print(a[i]+" ");
		}
	}
}
