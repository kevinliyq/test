package com.alu.study.algorithm;


/**
 * Insert Sort
 * imagine a array 'a' with N elements
 * 1. N-1 is a sorted element
 * 2. Insert a data at a[n] into the array with N-1 element
 * 
 * then we can leverage digui to complete it
 * 
 *  
 * @author yongqil
 *
 */
public class InsertSort {

	public static void main(String[] args){
		int[] a = {11, 8, 15, 3, 8, 9, 2, 6, 4, 7, 1};
		//, 15, 3, 8, 9, 2, 6, 4, 7, 1
		print("ori:",a);
		
		sort(a,0,a.length - 1);
		print("sorted:",a);
	}
	
	private static void sort(int[] a, int lo, int hi) {
		while(hi > lo){
			int key = a[hi];
			sort(a,lo,hi-1);
			insert(a,lo,hi-1,key);
			hi--;
		}
		
	}

	private static void insert(int[] a, int lo, int hi, int key) {
		if(lo > hi){
			return;
		}
		int i = lo;
		int j = hi;
		while(j >= i){
			if(a[j] >= key){
				a[j+1] = a[j];
				j--;
			}else{
				break;
			}
		}
		
		a[j+1] = key;
		
	}

	private static void print(String message, int[] a) {
		System.out.print(message);
		for(int i = 0; i < a.length; i++){
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
}
