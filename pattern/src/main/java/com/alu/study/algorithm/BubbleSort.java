package com.alu.study.algorithm;

/**
 * This is an example of BubbleSort
 */
public class BubbleSort {

	public static void main(String[] args){
		int[] a = {11, 8, 15, 3, 8, 9, 2, 6, 4, 7, 1};
		
		for(int i = 0; i < a.length; i++){
			for(int j=i+1; j < a.length; j++){
				if(a[i] > a[j]){
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
		for(int i = 0; i < a.length; i++){
			System.out.print(a[i]+" ");
		}
	}
}
